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


class AlertDialogConfiguration<B : ViewBinding>(context: Context) : AlertDialog.Builder(context) {
    lateinit var mAlertDialog: AlertDialog

    private var _binding: B? = null
    private val binding get() = _binding!!

    override fun show(): AlertDialog? {
        val content: View = LayoutInflater.from(context).inflate(R.layout.fragment_dialog, null)
        setView(content)
        mAlertDialog = super.show();
        return mAlertDialog;
    }

    override fun setNegativeButton(
        textId: Int,
        listener: DialogInterface.OnClickListener?
    ): AlertDialog.Builder {
        return super.setNegativeButton(textId, listener)
    }

}

class CustomAlertDialog(context: Context, private val onPositiveBtnClick: () -> Unit) :
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

        binding.addListBtn.setOnClickListener {
            onPositiveBtnClick()
        }

    }

    fun getTextFieldResult(): String {
        return binding.textInputLayout.editText?.editableText.toString()
    }


}