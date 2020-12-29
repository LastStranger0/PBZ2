package com.mtg.mypbz2.view.searchActivities

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import com.google.android.material.textfield.TextInputEditText
import com.mtg.mypbz2.R
import com.mtg.mypbz2.model.Days
import com.mtg.mypbz2.presenter.DBHelper
import com.mtg.mypbz2.presenter.adapter.DaysAdapter

class NumberOfCarActivity : AppCompatActivity() {

    private lateinit var startDate: TextInputEditText
    private lateinit var endDate: TextInputEditText
    private lateinit var result: Button
    private lateinit var listOfCars: ListView

    private lateinit var db: SQLiteDatabase
    private lateinit var databaseHelper: DBHelper
    private lateinit var cursor: Cursor
    private var days = mutableListOf<Days>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_of_car)
        setValues()
        db = databaseHelper.readableDatabase
        val day = DaysAdapter(this, R.layout.day_example, days)
        listOfCars.adapter = day
        result.setOnClickListener {
            cursor = db.rawQuery("SELECT *\n" +
                    "FROM ${DBHelper.TABLE_CONTACT3}\n" +
                    "WHERE DATE(${DBHelper.TL_DATE}) <= ?  AND DATE(${DBHelper.TL_DATE}) >= ? ", arrayOf(endDate.text.toString(), startDate.text.toString()))
            if(cursor.moveToFirst()){
                Log.d("number", cursor.getString(2))
            do {
                var b = true
                for (i in days) {
                    if (i.date == cursor.getString(5)) {
                        b = false
                    }
                }
                if (b) {
                    days.add(Days(cursor.getString(5), 0))

                }
            } while (cursor.moveToNext())}

            if(cursor.moveToFirst()){
            do {
                for (i in days) {
                    if (i.date == cursor.getString(5)) {
                        i.numberCars++
                    }
                }
            } while (cursor.moveToNext())}
            day.notifyDataSetChanged()
            Log.d("number", "${days.size}")
        }

    }

    private fun setValues(){
        startDate = findViewById(R.id.start_date)
        endDate = findViewById(R.id.end_date)
        listOfCars = findViewById(R.id.list_of_cars)
        result = findViewById(R.id.result_number)
        databaseHelper = DBHelper(applicationContext)
    }

}