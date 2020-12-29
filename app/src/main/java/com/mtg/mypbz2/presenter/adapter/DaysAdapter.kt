package com.mtg.mypbz2.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.textview.MaterialTextView
import com.mtg.mypbz2.R
import com.mtg.mypbz2.model.Days

class DaysAdapter(context: Context, resource: Int, day: MutableList<Days>): ArrayAdapter<Days>(context, resource, day) {
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var layout: Int = resource
    private var days = day

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(this.layout, parent, false)
        val dayNumber = view.findViewById<MaterialTextView>(R.id.day_number)
        val numberOfCars = view.findViewById<MaterialTextView>(R.id.number_of_cars)
        val day = days.get(position)
        dayNumber.text = day.date
        numberOfCars.text = day.numberCars.toString()
        return view
    }

}