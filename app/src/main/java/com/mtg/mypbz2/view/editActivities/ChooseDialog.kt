package com.mtg.mypbz2.view.editActivities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.mtg.mypbz2.R
import com.mtg.mypbz2.view.MainActivity

class ChooseDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog = AlertDialog.Builder(activity)
        val arg = arguments?.getInt("button", 0)
        val variant = arrayOf(
            getString(R.string.car),
            getString(R.string.police),
            getString(R.string.inspection)
        )
        return alertDialog.setTitle(R.string.choose_table)
            .setItems(variant) { dialog, which ->
                (activity as MainActivity).getDialog("$arg$which".toInt())
            }
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}