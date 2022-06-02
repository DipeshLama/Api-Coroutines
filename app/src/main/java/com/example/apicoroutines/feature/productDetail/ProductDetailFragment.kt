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
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentProductDetailBinding
import com.example.apicoroutines.feature.favourite.FavouriteViewModel
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.request.FavouriteRequest
import com.example.apicoroutines.feature.shared.model.response.Favourite
import com.example.apicoroutines.feature.shared.model.response.Product
import com.example.apicoroutines.utils.constants.ApiConstants
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment(),View.OnClickListener {
    private lateinit var binding: FragmentProductDetailBinding
    private val detailViewModel: ProductDetailViewModel by viewModels()
    private val favViewModel : FavouriteViewModel by viewModels()
    private var productId : Int? = null

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
        initListener()
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
        binding.txvDetailProductTitle.text = product?.title
        binding.txvDescription.text = product?.description?.let{HtmlCompat.fromHtml(it,0)}
        binding.txvDetailPrice.text = product?.unitPrice?.get(0)?.markedPrice.toString()
    }

    private fun onLoading (){
        binding.prgDetail.visibility = View.VISIBLE
        binding.layoutBottom.visibility = View.GONE
        binding.layoutDescription.visibility = View.GONE
    }

    private fun onLoadingFinish(){
        hideProgressBar()
        binding.layoutBottom.visibility = View.VISIBLE
        binding.layoutDescription.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.prgDetail.visibility = View.GONE
    }

    private fun addToCart(){
        Log.d("productid",productId.toString())
        favViewModel.addToFavourite(getAccessToken(), FavouriteRequest(productId ?:0) )
            .observe(viewLifecycleOwner){
                when(it.status){
                    Status.SUCCESS -> onAddToFavouriteSuccess(it)
                    Status.ERROR -> onAddTOFavouriteError(it.message)
                }
            }
    }

    private fun onAddToFavouriteSuccess(it: Resource<Response<BaseResponse<Favourite>>>) {
        it.data?.let {
            if(it.isSuccessful && it.body()?.data != null){
                showMessage("Added to favourite")
            }else{
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
        activity?.findViewById<BottomNavigationView>(R.id.btmNav)?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        activity?.findViewById<BottomNavigationView>(R.id.btmNav)?.visibility = View.VISIBLE
    }

    private fun initListener (){
        binding.cvDetailCardView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view){
            binding.cvDetailCardView -> addToCart()
        }
    }
}