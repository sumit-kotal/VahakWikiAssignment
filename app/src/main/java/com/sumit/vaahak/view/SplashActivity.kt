package com.sumit.vaahak.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.sumit.vaahak.R
import com.sumit.vaahak.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    val SPLASH_DELAY: Long = 3000 //Time delay to remove splash screen
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        /* Add Delay to show Login/Home Screen after Splash Screen*/
        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_DELAY)
    }
}