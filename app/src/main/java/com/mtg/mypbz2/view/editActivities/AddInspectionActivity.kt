package com.mtg.mypbz2.view.editActivities

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.mtg.mypbz2.R
import com.mtg.mypbz2.presenter.DBHelper

class AddInspectionActivity : AppCompatActivity() {

    companion object{
        const val CHOOSE_CAR = 111
        const val CHOOSE_POLICE = 222
        const val CAR_POLICE = "cp"
        const val CAR = 1111
        const val POLICE = 2222
    }

    private lateinit var chooseCar: Button
    private lateinit var choosePolice: Button
    private lateinit var addTLdate: TextInputEditText
    private lateinit var addTLresult: TextInputEditText
    private lateinit var addTLconclusion: TextInputEditText
    private lateinit var result: Button

    private lateinit var db: SQLiteDatabase
    private lateinit var databaseHelper: DBHelper
    private lateinit var cursor: Cursor
    private var arg = 0
    private var carID = 0
    private var policeID = 0
    private var carNumber = ""
    private var policeFIO = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inspection)
        val arguments = intent.extras
        if (arguments != null) {
            arg = arguments.getInt(SearchEditActivity.ID, 0)
        }
        setValues()
        db = databaseHelper.writableDatabase
        if (arg > 0){
            cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT3} WHERE ${DBHelper.TL_ID} =? ", arrayOf(arg.toString()))
            cursor.moveToFirst()
            carID = cursor.getInt(1)
            carNumber = cursor.getColumnName(2)
            policeID = cursor.getInt(3)
            policeFIO = cursor.getString(4)
            addTLdate.setText(cursor.getString(5))
            addTLresult.setText(cursor.getString(6))
            addTLconclusion.setText(cursor.getString(7))
            cursor.close()
        }
        result.setOnClickListener {
            save()
        }
        chooseCar.setOnClickListener {
            val intent = Intent(this, SearchEditActivity::class.java)
            intent.putExtra(CAR_POLICE, CAR)
            startActivityForResult(intent, CHOOSE_CAR)
        }
        choosePolice.setOnClickListener {
            val intent = Intent(this, SearchEditActivity::class.java)
            intent.putExtra(CAR_POLICE, POLICE)
            startActivityForResult(intent, CHOOSE_POLICE)
        }
    }

    private fun close(){
        val intent = Intent(this, SearchEditActivity::class.java)
        intent.putExtra(SearchEditActivity.ID, arg)
        db.close()
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun save(){
        val cv = ContentValues()
        cv.put(DBHelper.TL_CAR_ID, carID)
        cv.put(DBHelper.TL_CAR_NUMBER, carNumber)
        cv.put(DBHelper.TL_MENT_ID, policeID)
        cv.put(DBHelper.TL_MENT_FIO, policeFIO)
        cv.put(DBHelper.TL_DATE, addTLdate.text.toString())
        cv.put(DBHelper.TL_RESULT, addTLresult.text.toString())
        cv.put(DBHelper.TL_CONCLUSION, addTLconclusion.text.toString())
        arg = if (arg>0){
            db.update(DBHelper.TABLE_CONTACT3, cv, "${DBHelper.TL_ID} = $arg", null)
        }
        else{
            db.insert(DBHelper.TABLE_CONTACT3, null, cv).toInt()
        }
        close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            when (requestCode){
                CHOOSE_CAR -> {
                    carID = data?.getIntExtra(SearchEditActivity.ID, 0)!!
                    Log.d("AddInspectionActivity", carID.toString())
                    cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT1} WHERE ${DBHelper.CAR_ID} =?", arrayOf(carID.toString()))
                    cursor.moveToFirst()
                    chooseCar.text = cursor.getString(1)
                    carNumber = chooseCar.text.toString()
                }
                CHOOSE_POLICE -> {
                    policeID = data?.getIntExtra(SearchEditActivity.ID, 0)!!
                    cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT2} WHERE ${DBHelper.MENT_ID} =?", arrayOf(policeID.toString()))
                    cursor.moveToFirst()
                    choosePolice.text = cursor.getString(1)
                    policeFIO = choosePolice.text.toString()
                }
            }
        }
    }

    private fun setValues(){
        chooseCar = findViewById(R.id.choose_car)
        choosePolice = findViewById(R.id.choose_police)
        addTLdate = findViewById(R.id.add_tl_date)
        addTLresult = findViewById(R.id.add_tl_result)
        addTLconclusion = findViewById(R.id.add_tl_conclusion)
        result = findViewById(R.id.tl_result)
        databaseHelper = DBHelper(applicationContext)
    }
}