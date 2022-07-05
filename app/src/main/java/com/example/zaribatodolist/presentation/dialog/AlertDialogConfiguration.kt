package com.example.zaribatodolist.presentation.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.zaribatodolist.R

class AlertDialogConfiguration(
    context: Context,
    private val inflater: LayoutInflater
) : AlertDialog.Builder(context) {
    //    fun build() {
//        when (mode) {
//            "Add New List" -> {
//                val builder = AlertDialog.Builder(context)
//                builder.setTitle("Dialog")
//                val customLayout = inflater.inflate(R.layout.fragment_dialog, null);
//                builder.setView(customLayout);
//
//                val dialog = builder.create()
//                dialog.show()
//
//            }
//        }
//    }

    override fun setView(view: View?): AlertDialog.Builder {
        return super.setView(view)
    }
    fun setView(dialog: String) : View {

        lateinit var view: View
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Dialog")

        when (dialog) {
            "Add New List" -> {
                view = inflater.inflate(R.layout.fragment_dialog, null);
                return view
            }
        }
        builder.setView(view)
        builder.create().show()

        return view
    }


}