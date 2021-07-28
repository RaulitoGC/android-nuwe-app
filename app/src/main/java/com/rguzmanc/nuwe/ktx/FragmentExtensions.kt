package com.rguzmanc.nuwe.ktx

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateTo(@IdRes resource : Int){
    findNavController().navigate(resource)
}

fun Fragment.showToastMessage(message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}