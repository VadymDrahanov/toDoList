package com.example.zaribatodolist.presentation.dialog

import android.R
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.zaribatodolist.databinding.FragmentDialogBinding


class CustomAlertDialogWithTextField(context: Context) :
    AlertDialog(context) {

    private lateinit var binding: FragmentDialogBinding
    private lateinit var onPositiveButtonClick: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = FragmentDialogBinding.inflate(layoutInflater)
        //val view = binding.root
        //window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

//        binding.cancelBtn.setOnClickListener {
//            dismiss()
//        }
//
//        binding.addListBtn.setOnClickListener {
//            onPositiveButtonClick()
//        }
//
//        binding.textInputLayout.setOnFocusChangeListener { v, res ->
//            if (v.isFocused) {
//                Log.i("Log", "isiisisisis")
//                Toast.makeText(context, "To", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//

    }


    //    fun setTitle(title: String) {
//        super.setTitle(title)
//    }
//
//    fun setButtonText(text: String) {
//        binding.addListBtn.text = text
//    }
//
//
    fun setOnPositiveBtnClickListener(click: () -> Unit) {
        onPositiveButtonClick = click
    }

    //
//    fun getTextFieldResult(): String {
//        return binding.textInputLayout.editText?.text.toString()
//    }
//
    class MBuilder(context: Context) : AlertDialog.Builder(context) {

        private lateinit var dialog: AlertDialog

        override fun create(): AlertDialog {

            // We can't use Dialog's 3-arg constructor with the createThemeContextWrapper param,
            // so we always have to re-set the theme

            return super.create()
        }
//
//        override fun setView(view: View?): AlertDialog.Builder {
//            return super.setView(view)
//        }
//
//
//
//        override fun setView(view: View?): AlertDialog.Builder {
//            val mview = binding.root
//            return super.setView(view)
//        }

        override fun setPositiveButton(
            text: CharSequence?,
            listener: DialogInterface.OnClickListener?
        ): Builder {
            return super.setPositiveButton(text, listener)
        }

        override fun setPositiveButtonIcon(icon: Drawable?): Builder {
            return super.setPositiveButtonIcon(icon)
        }

        fun clickListener() {

        }


        override fun setPositiveButton(
            textId: Int,
            listener: DialogInterface.OnClickListener?
        ): Builder {
            return super.setPositiveButton(textId, listener)
        }
    }
}

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var onPositiveButtonClick: (text: String) -> Unit
    private lateinit var binding: FragmentDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDialogBinding.inflate(layoutInflater)

        setContentView(binding.root);

        //val btn = this.findViewById<Button>(R.id.add)
        binding.addListBtn.setOnClickListener {
            onPositiveButtonClick(binding.textInputLayout.editText!!.text.toString())
        }

        binding.cancelBtn.setOnClickListener{
            dismiss()
        }
    }

    fun setOnPositiveBtnClickListener(click: (text: String) -> Unit) {
        onPositiveButtonClick = click
    }
}