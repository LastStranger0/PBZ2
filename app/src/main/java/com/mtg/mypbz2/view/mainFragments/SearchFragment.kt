package com.mtg.mypbz2.view.mainFragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.mtg.mypbz2.R
import com.mtg.mypbz2.view.searchActivities.CheckInspectionActivity
import com.mtg.mypbz2.view.searchActivities.CheckPoliceActivity
import com.mtg.mypbz2.view.searchActivities.NumberOfCarActivity

class SearchFragment : Fragment() {

    private lateinit var numberOfCar: Button
    private lateinit var ment: Button
    private lateinit var historyCar: Button
    private lateinit var mView: View
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mView = inflater.inflate(R.layout.fragment_search, container, false)
        setValues()
        numberOfCar.setOnClickListener {
            val intent = Intent(mContext, NumberOfCarActivity::class.java)
            startActivity(intent)
        }
        ment.setOnClickListener {
            val intent = Intent(mContext, CheckPoliceActivity::class.java)
            startActivity(intent)
        }
        historyCar.setOnClickListener {
            val intent = Intent(mContext, CheckInspectionActivity::class.java)
            startActivity(intent)
        }
        return mView
    }

    private fun setValues(){
        numberOfCar = mView.findViewById(R.id.number_of_car_button)
        ment = mView.findViewById(R.id.ment_button)
        historyCar =  mView.findViewById(R.id.history_car)
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}