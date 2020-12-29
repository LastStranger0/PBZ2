package com.mtg.mypbz2.view.searchActivities

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.mtg.mypbz2.R
import com.mtg.mypbz2.model.Police
import com.mtg.mypbz2.presenter.DBHelper
import com.mtg.mypbz2.presenter.adapter.PoliceAdapter

class CheckPoliceActivity : AppCompatActivity() {
    private lateinit var dateChoose: TextInputEditText
    private lateinit var listOfMent: ListView
    private lateinit var button: FloatingActionButton
    private lateinit var policeAdapter: PoliceAdapter

    private lateinit var db: SQLiteDatabase
    private lateinit var databaseHelper: DBHelper
    private lateinit var cursor: Cursor
    private var police = mutableListOf<Police>()
    private var policeFIO = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_police)
        setValues()
        db = databaseHelper.readableDatabase
        policeAdapter = PoliceAdapter(this, R.layout.police_check_example, police)
        listOfMent.adapter = policeAdapter
        button.setOnClickListener {
            cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT3} WHERE DATE(${DBHelper.TL_DATE}) = ? ",
                    arrayOf(dateChoose.text.toString()))
            if (cursor.moveToFirst()) {
                Log.d("checkPolice", cursor.getString(4))
                do {
                    policeFIO.add(cursor.getString(4))
                } while (cursor.moveToNext())
            }

            for (i in policeFIO){
                var b = true
                for (j in police){
                    if (j.FIO == i){
                        b = false
                    }
                }
                if (b) {
                    cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT2} WHERE ${DBHelper.MENT_FIO} = ?",
                            arrayOf(i))
                        if (cursor.moveToFirst() && cursor.count > 0) {
                            Log.d("checkPolice", cursor.getString(1))
                            police.add(Police(cursor.getString(1), cursor.getString(3), mutableListOf()))
                        }
                    }
            }
            cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT3} WHERE DATE(${DBHelper.TL_DATE}) = ? ",
                    arrayOf(dateChoose.text.toString()))
            if (cursor.moveToFirst()) {
                do {
                    for (i in police) {
                        if (cursor.getString(4) == i.FIO) {
                            i.numbers.add(cursor.getString(2))
                        }
                    }
                } while (cursor.moveToNext())
            }
            policeAdapter.notifyDataSetChanged()
            Log.d("checkPolice", police.size.toString())
        }
    }

    private fun setValues(){
        dateChoose = findViewById(R.id.date_choose)
        listOfMent = findViewById(R.id.list_of_ment)
        button = findViewById(R.id.search_choose_button)
        databaseHelper = DBHelper(applicationContext)
    }
}