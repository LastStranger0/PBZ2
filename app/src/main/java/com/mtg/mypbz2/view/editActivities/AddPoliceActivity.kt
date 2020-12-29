package com.mtg.mypbz2.view.editActivities

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.mtg.mypbz2.R
import com.mtg.mypbz2.presenter.DBHelper

class AddPoliceActivity : AppCompatActivity() {


    private lateinit var addPoliceFIO: TextInputEditText
    private lateinit var addPolicePosition: TextInputEditText
    private lateinit var addPoliceRank: TextInputEditText
    private lateinit var result: Button

    private lateinit var db: SQLiteDatabase
    private lateinit var databaseHelper: DBHelper
    private lateinit var cursor: Cursor

    private var arg = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_police)
        val arguments = intent.extras
        if (arguments != null) {
            arg = arguments.getInt(SearchEditActivity.ID)
        }
        setValues()
        db = databaseHelper.writableDatabase
        if (arg > 0){
            cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT2} WHERE ${DBHelper.MENT_ID} =? ", arrayOf(arg.toString()))
            cursor.moveToFirst()
            addPoliceFIO.setText(cursor.getString(1))
            addPolicePosition.setText(cursor.getString(2))
            addPoliceRank.setText(cursor.getString(3))
            cursor.close()
        }
        result.setOnClickListener {
            save()
        }

    }

    private fun setValues(){
        addPoliceFIO = findViewById(R.id.add_police_fio)
        addPolicePosition = findViewById(R.id.add_police_position)
        addPoliceRank = findViewById(R.id.add_police_rank)
        result = findViewById(R.id.police_result)
        databaseHelper = DBHelper(applicationContext)
    }

    private fun save(){
        val cv = ContentValues()
        cv.put(DBHelper.MENT_FIO, addPoliceFIO.text.toString())
        cv.put(DBHelper.MENT_POSITION, addPolicePosition.text.toString())
        cv.put(DBHelper.MENT_RANK, addPoliceRank.text.toString())
        arg = if (arg > 0){
            db.update(DBHelper.TABLE_CONTACT2, cv, "${DBHelper.MENT_ID} = $arg", null)
        }
        else{
            db.insert(DBHelper.TABLE_CONTACT2, null, cv).toInt()
        }
        close()
    }

    private fun close(){
        val intent = Intent(this, SearchEditActivity::class.java)
        intent.putExtra(SearchEditActivity.ID, arg)
        db.close()
        setResult(RESULT_OK, intent)
        finish()
    }
}