package com.example.apicoroutines.feature.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentCartBinding
import com.example.apicoroutines.feature.shared.adapter.CartAdapter
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.listener.CartUpdateListener
import com.example.apicoroutines.feature.shared.model.request.UpdateCart
import com.example.apicoroutines.feature.shared.model.response.Cart
import com.example.apicoroutines.feature.shared.model.response.CartProducts
import com.example.apicoroutines.utils.helper.DecimalHelper
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class CartFragment : BaseFragment(), CartUpdateListener, View.OnClickListener {
    private lateinit var binding: FragmentCartBinding
    private var list = arrayListOf<CartProducts>()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_cart,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel
        initListener()
        initDialog()
        setRecyclerView()
        getCart(getAccessToken())
    }

    private fun getCart(token: String) {
        cartViewModel.getUserCart(token)
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onCartGetSuccess(it)
                    Status.ERROR -> onCartGetError(it.message)
                    Status.LOADING -> {
                        visibleProgressBar(true)
                        showLayout(false)
                    }
                }
            }
    }

    private fun onCartGetError(msg: String?) {
        showMessage(msg)
        visibleProgressBar(false)
        showLayout(false)
    }

    private fun onCartGetSuccess(it: Resource<Response<BaseResponse<Cart>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                setView(it.body()?.data)
                list.clear()
                list.addAll(it.body()?.data?.cartProducts ?: emptyList())
                cartAdapter.notifyItemRangeInserted(0, list.count())
                visibleProgressBar(false)
                showLayout(true)
            }
        }
    }

    private fun setView(data: Cart?) {
        binding.cart = data
        binding.executePendingBindings()
    }


    private fun setRecyclerView() {
        cartAdapter = CartAdapter(list, this)
        binding.ryvCart.apply {
            adapter = cartAdapter
            itemAnimator = null
        }
    }

    override fun onCartIncrease(position: Int, cartProductId: Int?) {
        cartIncrease(position, cartProductId)
    }

    override fun onCartDecrease(position: Int, cartProductId: Int?) {
        cartDecrease(position, cartProductId)
    }

    override fun onDelete(position: Int, cartProductId: Int?) {
        deleteCart(position, cartProductId!!)
    }

    private fun cartIncrease(position: Int, cartProductId: Int?) {
        updateCart(position,
            cartProductId,
            list[position].quantity?.plus(1))
        notifyAdapter(position)
    }

    private fun cartDecrease(position: Int, cartProductId: Int?) {
        updateCart(position,
            cartProductId,
            list[position].quantity?.minus(1))
        notifyAdapter(position)
    }

    private fun updateCart(position: Int, cartProductId: Int?, quantity: Int?) {
        cartViewModel.updateCart(getAccessToken(),
            cartProductId ?: 0,
            UpdateCart(quantity, "testing"))
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> dialog.show()
                    Status.SUCCESS -> onCartUpdateSuccess(it)
                    Status.ERROR -> onCartUpdateError(it.message)
                }
            }
    }

    private fun onCartUpdateSuccess(it: Resource<Response<BaseResponse<CartProducts>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                getCart(getAccessToken())
                dialog.dismiss()
            } else {
                dialog.dismiss()
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onCartUpdateError(msg: String?) {
        dialog.dismiss()
        showMessage(msg)
    }

    private fun deleteCart(position: Int, cartProductId: Int) {
        cartViewModel.deleteCart(getAccessToken(), cartProductId)
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> dialog.show()
                    Status.ERROR -> onDeleteError(it.message)
                    Status.SUCCESS -> onDeleteSuccess(it, position)
                }
            }
    }

    private fun onDeleteSuccess(it: Resource<Response<String>>, position: Int) {
        it.data?.let {
            if (it.isSuccessful && it.code() == 204) {
                dialog.dismiss()
                list.removeAt(position)
                showMessage("Delete successful")
                notifyAdapter(position)
                getCart(getAccessToken())
            } else {
                dialog.dismiss()
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onDeleteError(msg: String?) {
        dialog.dismiss()
        showMessage(msg)
    }

    private fun notifyAdapter(position: Int) {
        cartAdapter.notifyItemChanged(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBottomNavBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        showBottomNavBar()
    }


    private fun visibleProgressBar(visible: Boolean) {
        when {
            visible -> binding.prgCart.visibility = View.VISIBLE

            else -> binding.prgCart.visibility = View.GONE
        }
    }

    private fun showLayout(visible: Boolean) {
        when {
            visible -> binding.layoutCart.visibility = View.VISIBLE
            else -> binding.layoutCart.visibility = View.GONE
        }
    }

    private fun initListener(){
        binding.layoutContinue.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.layoutContinue -> {
                findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
            }
        }

    }
}