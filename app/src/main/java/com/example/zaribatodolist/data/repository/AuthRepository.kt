package com.example.zaribatodolist.data.repository

import android.content.Context
import android.widget.Toast

class AuthRepository(private val context: Context) {

    fun isWorking(){
        Toast.makeText(context, "Its a toast!", Toast.LENGTH_SHORT).show()
    }
}