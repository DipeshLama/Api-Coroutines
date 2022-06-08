package com.example.apicoroutines.feature.shared.base

import android.app.Dialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.ui.onNavDestinationSelected
import com.example.apicoroutines.R
import com.example.apicoroutines.feature.cart.CartViewModel
import com.example.apicoroutines.feature.main.MainActivity
import com.example.apicoroutines.feature.shared.adapter.CartAdapter
import com.example.apicoroutines.feature.shared.model.response.Cart
import com.example.apicoroutines.feature.shared.model.response.CartProducts
import com.example.apicoroutines.feature.shared.model.response.ErrorResponse
import com.example.apicoroutines.utils.globalUtils.PreferenceUtils
import com.example.apicoroutines.utils.helper.NetworkHelper
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import retrofit2.Response
import java.math.BigDecimal
import java.math.RoundingMode

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {
    protected val cartViewModel: CartViewModel by viewModels()
    protected lateinit var dialog: Dialog

    protected fun getError(error: String?): String? {
        val gson = Gson()
        val root = gson.fromJson(error, ErrorResponse::class.java)
        val errorMessages = root?.errors

        return if (!errorMessages.isNullOrEmpty()) {
            errorMessages[0]?.message
        } else
            "Default error"
    }

    protected fun showMessage(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            .show()
    }

    protected fun checkIsOnline() =
        NetworkHelper.isNetworkConnected(requireContext())

    protected fun getAccessToken() = PreferenceUtils.getAccessToken(requireContext())


    fun getUserCart(token: String) {
        cartViewModel.getUserCart(token)
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onCartRetrieveSuccess(it)
                    Status.ERROR -> showMessage(it.message)
                }
            }
    }

    private fun onCartRetrieveSuccess(it: Resource<Response<BaseResponse<Cart>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {

                (activity as MainActivity).cartQuantity =
                    if (it.body()?.data?.cartProducts != null) {
                        it.body()?.data?.cartProducts?.size ?: 0
                    } else 0

                (activity as MainActivity).invalidateOptionsMenu()

            } else {
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    protected fun initDialog() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)
    }

    protected fun showBottomNavBar (){
        activity?.findViewById<BottomNavigationView>(R.id.btmNav)?.visibility = View.VISIBLE
    }

    protected fun hideBottomNavBar (){
        activity?.findViewById<BottomNavigationView>(R.id.btmNav)?.visibility = View.GONE
    }


}