package com.example.zaribatodolist.presentation.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.example.zaribatodolist.R
import com.example.zaribatodolist.databinding.FragmentDialogBinding

class CustomAlertDialogWithTextField(context: Context) :
    AlertDialog(context) {

    private lateinit var binding: FragmentDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentDialogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

    }

    fun setTitle(title: String) {
        setTitle(title)
    }

    fun setButtonText(text: String) {
        binding.addListBtn.text = text
    }

    fun setOnPositiveBtnClickListener(onPositiveBtnClick: () -> Unit) {
        binding.addListBtn.setOnClickListener {
            onPositiveBtnClick()
        }
    }

    fun getTextFieldResult(): String {
        return binding.textInputLayout.editText?.editableText.toString()
    }


}