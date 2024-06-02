package com.dicoding.asclepius.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.asclepius.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val splashScreenDuration = 3000L
        val moveMainActivityIntent = Intent(this, MainActivity::class.java)
        lifecycleScope.launch(Dispatchers.Main) {
            delay(splashScreenDuration)
            startActivity(moveMainActivityIntent)
            finish()
        }

    }
}