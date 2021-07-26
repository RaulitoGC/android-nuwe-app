package com.rguzmanc.nuwe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(AuthenticationActivity.getIntent(this@SplashActivity))
    }
}