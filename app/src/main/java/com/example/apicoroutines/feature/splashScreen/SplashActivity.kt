package com.example.apicoroutines.feature.splashScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.ActivitySplashBinding
import com.example.apicoroutines.feature.main.MainActivity
import com.example.apicoroutines.utils.router.Router

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
//        rotateHandler()
        splashDelayHandler()
    }

    private fun splashDelayHandler() {
        Handler().postDelayed({
            navigateToLanding()
        }, 4000)
    }

    private fun navigateToLanding() {
        Router.navigateActivity(this, true, MainActivity::class)
    }

    private fun rotateHandler() {
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_splash)
        binding.imvOuterLogo.startAnimation(rotateAnimation)
    }
}