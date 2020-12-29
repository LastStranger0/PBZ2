package com.mtg.mypbz2.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.textview.MaterialTextView
import com.mtg.mypbz2.R
import com.mtg.mypbz2.model.Police

class PoliceAdapter(context: Context, resId: Int, policeList: MutableList<Police>): ArrayAdapter<Police>(context,resId, policeList) {
    private val layoutInflater = LayoutInflater.from(context)
    private val layout = resId
    private val police = policeList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = layoutInflater.inflate(this.layout, parent, false)
        val checkFIO = view.findViewById<MaterialTextView>(R.id.checkPoliceFIO)
        val checkRank = view.findViewById<MaterialTextView>(R.id.checkPoliceRank)
        val numbers = view.findViewById<ListView>(R.id.list_of_numbers)
        val pol = police[position]
        checkFIO.text = pol.FIO
        checkRank.text = pol.Rank
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, pol.numbers)
        numbers.adapter = adapter
        return view
    }
}