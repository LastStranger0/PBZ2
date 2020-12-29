package com.mtg.mypbz2.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.mtg.mypbz2.R
import com.mtg.mypbz2.model.Car

class CarAdapter(var carList: MutableList<Car>, var context: Context): RecyclerView.Adapter<CarAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var carNumber: MaterialTextView = view.findViewById(R.id.car_number)
        var carColor: MaterialTextView = view.findViewById(R.id.car_color)
        var carMark: MaterialTextView = view.findViewById(R.id.car_mark)
        var carFIO: MaterialTextView = view.findViewById(R.id.car_FIO)
        var carDriverLicense: MaterialTextView = view.findViewById(R.id.car_driver_license)

    }

    private var layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.car_example, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = carList[position]
        holder.carNumber.text = car.carNumber
        holder.carColor.text = car.carColor
        holder.carMark.text = car.carMark
        holder.carFIO.text = car.carFIO
        holder.carDriverLicense.text = car.carDriverLicense
    }

    override fun getItemCount(): Int {
        return carList.size
    }
}