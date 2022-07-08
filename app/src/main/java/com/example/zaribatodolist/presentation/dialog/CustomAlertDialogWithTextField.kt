package com.example.zaribatodolist.presentation.dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import com.example.zaribatodolist.databinding.FragmentDialogBinding


class CustomAlertDialogWithTextField(context: Context) :
    AlertDialog(context) {

    private lateinit var binding: FragmentDialogBinding
    private lateinit var onPositiveButtonClick: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentDialogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

        binding.addListBtn.setOnClickListener {
            onPositiveButtonClick()
        }

        binding.textInputLayout.setOnFocusChangeListener{ v , res ->
            if(v.isFocused){
                Log.i("Log", "isiisisisis")
                Toast.makeText(context, "To", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setTitle(title: String) {
        super.setTitle(title)
    }

    fun setButtonText(text: String) {
        binding.addListBtn.text = text
    }


    fun setOnPositiveBtnClickListener(click: () -> Unit) {
        onPositiveButtonClick = click
    }

    fun getTextFieldResult(): String {
        return binding.textInputLayout.text.toString()
    }

}