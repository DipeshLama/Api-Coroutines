package com.example.apicoroutines.feature.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.ActivityMainBinding
import com.example.apicoroutines.feature.home.HomeViewModel
import com.example.apicoroutines.feature.shared.base.BaseActivity
import com.example.apicoroutines.feature.shared.adapter.HomeScreenAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    var cartQuantity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initController()
    }

    private fun initController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frgHome)
        navController = navHostFragment!!.findNavController()
        AppBarConfiguration(navController.graph)
        binding.btmNav.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val menuItem = menu.findItem(R.id.cartFragment)
        val actionView = menuItem.actionView
        val cartBadge = actionView.findViewById<TextView>(R.id.txvCartBadge)

        menuItem.actionView.setOnClickListener{
            onOptionsItemSelected(menuItem)
        }
        when (cartQuantity) {
            0 -> cartBadge.visibility = View.GONE

            else -> cartBadge.text = cartQuantity.toString()
        }

        invalidateOptionsMenu()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}