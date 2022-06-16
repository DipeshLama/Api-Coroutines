package com.example.apicoroutines.feature.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentProductDetailBinding
import com.example.apicoroutines.feature.favourite.FavouriteViewModel
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.request.CartRequest
import com.example.apicoroutines.feature.shared.model.request.FavouriteRequest
import com.example.apicoroutines.feature.shared.model.response.AddToCart
import com.example.apicoroutines.feature.shared.model.response.Favourite
import com.example.apicoroutines.feature.shared.model.response.Product
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import java.math.BigDecimal
import java.math.RoundingMode

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentProductDetailBinding
    private val detailViewModel: ProductDetailViewModel by viewModels()
    private val favViewModel: FavouriteViewModel by viewModels()
    private var product: Product? = null
    private var quantity = 1

    var observableQuantity: ObservableInt = object : ObservableInt(quantity) {
        override fun get(): Int {
            return quantity
        }
    }

    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_product_detail,
            container,
            false)
        binding.handler = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel
        favViewModel
        cartViewModel
        binding.onButtonClick = this
        initListener()
        initDialog()
        getProductDetail(args.productId)
        shineAnimation()
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
        binding.product = product
        binding.executePendingBindings()

//        setQuantityIntoView()
//        setTotalPrice()
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

    fun addToCart() {
        callAddToCartApi(CartRequest(
            productId = args.productId,
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
                    Status.LOADING -> showHideBtnProgressBar(true)
                }
            }
    }

    private fun onAddToCartError(msg: String?) {
        showHideBtnProgressBar(false)
        showMessage(msg)
    }

    private fun onAddToCartSuccess(it: Resource<Response<BaseResponse<AddToCart>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                showHideBtnProgressBar(false)
                showMessage("Added to cart")
            } else {
                onAddToCartError(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun addToFavourite() {
        favViewModel.addToFavourite(getAccessToken(), FavouriteRequest(args.productId))
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onAddToFavouriteSuccess(it)
                    Status.ERROR -> onAddTOFavouriteError(it.message)
                    Status.LOADING -> dialog.show()
                }
            }
    }

    private fun onAddToFavouriteSuccess(it: Resource<Response<BaseResponse<Favourite>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                dialog.dismiss()
                showMessage("Added to favourite")
            } else {
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onAddTOFavouriteError(msg: String?) {
        dialog.dismiss()
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
        binding.imvIncreaseQuantity.setOnClickListener(this)
        binding.imvDecreaseQuantity.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.cvDetailCardView -> addToFavourite()
            binding.imvIncreaseQuantity -> increaseQuantity()
            binding.imvDecreaseQuantity -> decreaseQuantity()
        }
    }

    private fun increaseQuantity() {
        quantity++
        observableQuantity.notifyChange()
//        setQuantityIntoView()
    }

    private fun decreaseQuantity() {
        if (quantity > 1) {
            quantity--
            observableQuantity.notifyChange()
        }
//        setQuantityIntoView()
    }

//    private fun setQuantityIntoView() {
//        binding.txvDetailQuantity.text = quantity.toString()
//        setTotalPrice()
//    }

//    private fun setTotalPrice() {
//        val productPrice = product?.unitPrice?.get(0)?.markedPrice
//        binding.btnAddToCart.text =
//            String.format(getString(R.string.add_rs), "${(productPrice?.times(quantity))}")
//    }

    private fun shineAnimation() {
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.left_right)
        binding.shine.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.shine.startAnimation(anim)
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
    }

    private fun showHideBtnProgressBar(show: Boolean) {
        when {
            show -> binding.prgAddToCart.visibility = View.VISIBLE
            else -> binding.prgAddToCart.visibility = View.GONE
        }
    }
}