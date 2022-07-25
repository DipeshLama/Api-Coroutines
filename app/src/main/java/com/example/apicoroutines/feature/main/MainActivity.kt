package com.example.apicoroutines.feature.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import shortbread.Shortcut

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    var cartQuantity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initController()
//        initNavHost()
//        binding.setUpBottomNavigation()
    }

    private fun initNavHost() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frgHome) as NavHostFragment
        navController = navHostFragment.navController
    }

//    private fun ActivityMainBinding.setUpBottomNavigation() {
//        val bottomNavigationItems = mutableListOf(
//            CurvedBottomNavigation.Model(HOME_ITEM,
//                getString(R.string.home),
//                R.drawable.ic_home_iconly),
//            CurvedBottomNavigation.Model(CATEGORIES_ITEM,
//                getString(R.string.categories),
//                R.drawable.ic_category_iconly),
//            CurvedBottomNavigation.Model(FAQ_ITEM,
//                getString(R.string.offers),
//                R.drawable.ic_offers),
//            CurvedBottomNavigation.Model(FAVOURITE_ITEM,
//                getString(R.string.favourite),
//                R.drawable.ic_heart),
//            CurvedBottomNavigation.Model(MORE_ITEM, getString(R.string.more), R.drawable.ic_more)
//        )
//        binding.btmNav.apply {
//            bottomNavigationItems.forEach { add(it) }
//            setOnClickMenuListener {
//                navController.navigate(it.id)
//            }
//            // optional
//            setupNavController(navController)
//        }
//    }

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

        menuItem.actionView.setOnClickListener {
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

    companion object {
        // you can put any unique id here, but because I am using Navigation Component I prefer to put it as
        // the fragment id.
        const val HOME_ITEM = R.id.homeFragment
        const val CATEGORIES_ITEM = R.id.categoriesFragment
        const val FAQ_ITEM = R.id.faqFragment
        const val FAVOURITE_ITEM = R.id.favouriteFragment
        const val MORE_ITEM = R.id.moreFragment
    }

    @Shortcut(
        id = "cart",
        icon = R.drawable.ic_cart_light,
        shortLabel = "My Cart"
    )
    fun navigateToCart(){
        navController.navigate(R.id.cartFragment)
    }
}