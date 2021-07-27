package com.rguzmanc.nuwe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rguzmanc.nuwe.databinding.ActivityAuthBinding

class AuthenticationActivity: AppCompatActivity() {

    companion object{
        fun getIntent(context: Context) : Intent{
            return Intent(context, AuthenticationActivity::class.java)
        }
    }

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
    }
}