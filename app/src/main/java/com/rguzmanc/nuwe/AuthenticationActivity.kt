package com.rguzmanc.nuwe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AuthenticationActivity: AppCompatActivity() {

    companion object{
        fun getIntent(context: Context) : Intent{
            return Intent(context, AuthenticationActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}