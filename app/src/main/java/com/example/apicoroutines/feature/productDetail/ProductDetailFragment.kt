package com.example.apicoroutines.feature.productDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentProductDetailBinding
import com.example.apicoroutines.feature.cart.CartViewModel
import com.example.apicoroutines.feature.favourite.FavouriteViewModel
import com.example.apicoroutines.feature.paymentOptions.PaymentOptionsFragment
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.request.CartRequest
import com.example.apicoroutines.feature.shared.model.request.FavouriteRequest
import com.example.apicoroutines.feature.shared.model.response.AddToCart
import com.example.apicoroutines.feature.shared.model.response.Favourite
import com.example.apicoroutines.feature.shared.model.response.Product
import com.example.apicoroutines.utils.constants.ApiConstants
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentProductDetailBinding
    private val detailViewModel: ProductDetailViewModel by viewModels()
    private val favViewModel: FavouriteViewModel by viewModels()
    private var productId: Int? = null
    private var product: Product? = null
    private var quantity = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_product_detail,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel
        favViewModel
        cartViewModel
        initListener()
        initDialog()
        productId = arguments?.getInt(ApiConstants.productId)
        productId?.let { getProductDetail(it) }
    }

    private fun getProductDetail(productId: Int) {
        detailViewModel.getProductDetail(productId)
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onDetailReceived(it)
                    Status.ERROR -> onError(it.message)
                    Status.LOADING -> onLoading()
                }
            }
    }

    private fun onError(message: String?) {
        showMessage(message)
        hideProgressBar()
    }

    private fun onDetailReceived(it: Resource<Response<BaseResponse<Product>>>) {
        it.data?.let {
            when {
                it.isSuccessful && it.body()?.data != null -> {
                    setViews(it.body()?.data)
                    onLoadingFinish()
                }
                else -> {
                    hideProgressBar()
                    showMessage(getError(it.errorBody()?.string()))
                }
            }
        }
    }

    private fun setViews(product: Product?) {
        this.product = product
        binding.txvDetailProductTitle.text = product?.title
        binding.txvDescription.text = product?.description?.let { HtmlCompat.fromHtml(it, 0) }
        binding.txvDetailPrice.text = product?.unitPrice?.get(0)?.markedPrice.toString()
        Glide.with(requireContext()).load(product?.images?.get(0)?.imageName).into(binding.imvDetailMain)
        Glide.with(requireContext()).load(product?.images?.get(0)?.imageName).into(binding.imvDetailMid)
        setQuantityIntoView()
        setTotalPrice()
    }

    private fun onLoading() {
        binding.prgDetail.visibility = View.VISIBLE
        binding.layoutBottom.visibility = View.GONE
        binding.layoutDescription.visibility = View.GONE
    }

    private fun onLoadingFinish() {
        hideProgressBar()
        binding.layoutBottom.visibility = View.VISIBLE
        binding.layoutDescription.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.prgDetail.visibility = View.GONE
    }

    private fun addToCart() {
        callAddToCartApi(CartRequest(
            productId = productId,
            priceId = this.product?.unitPrice?.get(0)?.id,
            quantity = quantity,
            note = "testing"
        ))
    }

    private fun callAddToCartApi(request: CartRequest) {
        cartViewModel.addToCart(getAccessToken(), request)
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onAddToCartSuccess(it)
                    Status.ERROR -> onAddToCartError(it.message)
                    Status.LOADING -> dialog.show()
                }
            }
    }

    private fun onAddToCartError(msg: String?) {
        showMessage(msg)
    }

    private fun onAddToCartSuccess(it: Resource<Response<BaseResponse<AddToCart>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                dialog.dismiss()
                showMessage("Added to cart")
            } else {
                showMessage(getError(it.errorBody()?.string()))
                dialog.dismiss()
            }
        }
    }

    private fun addToFavourite() {
        Log.d("productid", productId.toString())
        favViewModel.addToFavourite(getAccessToken(), FavouriteRequest(productId ?: 0))
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onAddToFavouriteSuccess(it)
                    Status.ERROR -> onAddTOFavouriteError(it.message)
                }
            }
    }

    private fun onAddToFavouriteSuccess(it: Resource<Response<BaseResponse<Favourite>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                showMessage("Added to favourite")
            } else {
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onAddTOFavouriteError(msg: String?) {
        showMessage(msg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        hideBottomNavBar()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        showBottomNavBar()
    }

    private fun initListener() {
        binding.cvDetailCardView.setOnClickListener(this)
        binding.btnAddToCart.setOnClickListener(this)
        binding.imvIncreaseQuantity.setOnClickListener(this)
        binding.imvDecreaseQuantity.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.cvDetailCardView -> addToFavourite()
            binding.btnAddToCart -> addToCart()
            binding.imvIncreaseQuantity -> increaseQuantity()
            binding.imvDecreaseQuantity -> decreaseQuantity()
        }
    }

    private fun increaseQuantity() {
        quantity++
        setQuantityIntoView()
    }

    private fun decreaseQuantity() {
        if (quantity > 1) {
            quantity--
        }
        setQuantityIntoView()
    }

    private fun setQuantityIntoView() {
        binding.txvDetailQuantity.text = quantity.toString()
        setTotalPrice()
    }

    private fun setTotalPrice() {
        val productPrice = product?.unitPrice?.get(0)?.markedPrice
        binding.btnAddToCart.text = "Add Rs. ${(productPrice?.times(quantity))}"
    }
}