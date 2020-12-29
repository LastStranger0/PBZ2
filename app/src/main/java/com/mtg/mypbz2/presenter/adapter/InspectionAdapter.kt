package com.mtg.mypbz2.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.textview.MaterialTextView
import com.mtg.mypbz2.R
import com.mtg.mypbz2.model.Inspection

class InspectionAdapter(context: Context, resId: Int, inspectionList: MutableList<Inspection>):
        ArrayAdapter<Inspection>(context, resId, inspectionList){
    private val layoutInflater = LayoutInflater.from(context)
    private val layout = resId
    private val inspection = inspectionList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = layoutInflater.inflate(this.layout, parent, false)
        val dateInspection = view.findViewById<MaterialTextView>(R.id.date_inspection_example)
        val resultInspection = view.findViewById<MaterialTextView>(R.id.result_inspection)
        val insp = inspection[position]
        dateInspection.text = insp.date
        resultInspection.text = insp.result

        return view
    }
}