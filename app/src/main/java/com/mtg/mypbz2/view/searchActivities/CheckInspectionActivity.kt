package com.mtg.mypbz2.view.searchActivities

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inspector.InspectionCompanion
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.mtg.mypbz2.R
import com.mtg.mypbz2.model.Inspection
import com.mtg.mypbz2.presenter.DBHelper
import com.mtg.mypbz2.presenter.adapter.InspectionAdapter

class CheckInspectionActivity : AppCompatActivity() {

    private lateinit var searchButton: FloatingActionButton
    private lateinit var searchField: TextInputEditText
    private lateinit var list: ListView
    private lateinit var adapter: InspectionAdapter
    private var inspectionList = mutableListOf<Inspection>()

    private lateinit var db: SQLiteDatabase
    private lateinit var databaseHelper: DBHelper
    private lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_inspection)
        setValues()
        db = databaseHelper.readableDatabase
        adapter = InspectionAdapter(this, R.layout.check_inspection_example, inspectionList)
        list.adapter = adapter
        searchButton.setOnClickListener{
            cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT1} WHERE ${DBHelper.CAR_ENGINE_NUMBER} = ?",
                    arrayOf(searchField.text.toString()))
            val numbers = mutableListOf<Int>()
            if (cursor.moveToFirst()){
                do {
                    numbers.add(cursor.getInt(0))
                } while (cursor.moveToNext())
            }

            for (i in numbers){
                cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT3} WHERE ${DBHelper.TL_CAR_ID} = ?",
                arrayOf(i.toString()))
                if(cursor.moveToFirst()){
                    do {
                        inspectionList.add(Inspection(cursor.getString(5), cursor.getString(6)))
                    } while (cursor.moveToNext())
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun setValues(){
        searchButton = findViewById(R.id.search_button_engine)
        searchField = findViewById(R.id.engine_choose)
        list = findViewById(R.id.list_of_inspection)
        databaseHelper = DBHelper(applicationContext)
    }
}