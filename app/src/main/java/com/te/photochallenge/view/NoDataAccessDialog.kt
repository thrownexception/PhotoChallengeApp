package com.te.photochallenge.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.te.photochallenge.R


class NoDataAccessDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        builder.setTitle("Problem encountered")
            .setMessage(R.string.dialog_question)
            .setPositiveButton(R.string.wait) { _, _ ->
                this@NoDataAccessDialog.getDialog()?.cancel()
            }
            .setNegativeButton(R.string.close_app) { _, _ ->
                val closeIntent = Intent(Intent.ACTION_MAIN)
                closeIntent.addCategory(Intent.CATEGORY_HOME)
                closeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(closeIntent)
            }
        return builder.create()
    }
}