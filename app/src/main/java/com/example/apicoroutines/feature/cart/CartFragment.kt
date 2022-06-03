package com.example.apicoroutines.feature.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentCartBinding
import com.example.apicoroutines.feature.shared.adapter.CartAdapter
import com.example.apicoroutines.feature.shared.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment() {
    private lateinit var binding: FragmentCartBinding
    private var list = cartList

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
        setRecyclerView()
        getUserCart(getAccessToken())
    }

    private fun setRecyclerView() {
        val cartAdapter = CartAdapter(list)
        this.cartAdapter = cartAdapter
        binding.ryvCart.adapter = cartAdapter
    }
}