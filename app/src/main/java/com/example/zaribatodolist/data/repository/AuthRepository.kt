package com.example.zaribatodolist.data.repository

import android.content.Context
import android.widget.Toast

class AuthRepository(private val context: Context) {

    fun getString(userName : String) : String{
        return "Hello, $userName"
    }
}