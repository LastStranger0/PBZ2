package com.mtg.mypbz2.view.editActivities

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.mtg.mypbz2.R
import com.mtg.mypbz2.presenter.DBHelper

class AddCarActivity : AppCompatActivity() {

    private lateinit var db: SQLiteDatabase
    private lateinit var databaseHelper: DBHelper
    private lateinit var cursor: Cursor
    private var arg = 0

    private lateinit var addCarNumber: TextInputEditText
    private lateinit var addCarEngine: TextInputEditText
    private lateinit var addCarColor: TextInputEditText
    private lateinit var addCarMark: TextInputEditText
    private lateinit var addTechPassport: TextInputEditText
    private lateinit var addCarDriverLicense: TextInputEditText
    private lateinit var addDriverFIO: TextInputEditText
    private lateinit var addDriverAddress: TextInputEditText
    private lateinit var addDriverYear: TextInputEditText
    private lateinit var addDriverSex: TextInputEditText
    private lateinit var result: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)
        val arguments = intent.extras
        if (arguments != null) {
            arg = arguments.getInt(SearchEditActivity.ID, 0)
        }
        setValues()
        db = databaseHelper.writableDatabase
        if (arg > 0){
            cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT1} WHERE ${DBHelper.CAR_ID} =?", arrayOf(arg.toString()))
            cursor.moveToFirst()
            addCarNumber.setText(cursor.getString(1))
            addCarEngine.setText(cursor.getInt(2).toString())
            addCarColor.setText(cursor.getString(3))
            addCarMark.setText(cursor.getString(4))
            addTechPassport.setText(cursor.getInt(5).toString())
            addCarDriverLicense.setText(cursor.getInt(6).toString())
            addDriverFIO.setText(cursor.getString(7))
            addDriverAddress.setText(cursor.getString(8))
            addDriverYear.setText(cursor.getInt(9).toString())
            addDriverSex.setText(cursor.getString(10))
            cursor.close()
        }
        result.setOnClickListener {
            save()
        }
    }

    private fun save(){
        val cv = ContentValues()
        cv.put(DBHelper.CAR_NUMBER, addCarNumber.text.toString())
        cv.put(DBHelper.CAR_ENGINE_NUMBER, addCarEngine.text.toString().toInt())
        cv.put(DBHelper.CAR_COLOR, addCarColor.text.toString())
        cv.put(DBHelper.CAR_MARK, addCarMark.text.toString())
        cv.put(DBHelper.CAR_PASSPORT, addTechPassport.text.toString().toInt())
        cv.put(DBHelper.CAR_DRIVER_LICENSE, addCarDriverLicense.text.toString().toInt())
        cv.put(DBHelper.CAR_DRIVER_FIO, addDriverFIO.text.toString())
        cv.put(DBHelper.CAR_DRIVER_ADDRESS, addDriverAddress.text.toString())
        cv.put(DBHelper.CAR_DRIVER_YEAR, addDriverYear.text.toString().toInt())
        cv.put(DBHelper.CAR_DRIVER_SEX, addDriverSex.text.toString())
        arg = if (arg>0){
            db.update(DBHelper.TABLE_CONTACT1, cv, "${DBHelper.CAR_ID} = $arg", null)
        }
        else{
            db.insert(DBHelper.TABLE_CONTACT1, null, cv).toInt()
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

    private fun setValues(){
        addCarNumber = findViewById(R.id.add_car_number)
        addCarEngine = findViewById(R.id.add_car_engine)
        addCarColor = findViewById(R.id.add_car_color)
        addCarMark = findViewById(R.id.add_car_mark)
        addTechPassport = findViewById(R.id.add_tech_passport)
        addCarDriverLicense = findViewById(R.id.add_car_driver_license)
        addDriverFIO = findViewById(R.id.add_driver_fio)
        addDriverAddress = findViewById(R.id.add_driver_address)
        addDriverYear = findViewById(R.id.add_driver_year)
        addDriverSex = findViewById(R.id.add_driver_sex)
        result = findViewById(R.id.car_result)
        databaseHelper = DBHelper(applicationContext)
    }
}

