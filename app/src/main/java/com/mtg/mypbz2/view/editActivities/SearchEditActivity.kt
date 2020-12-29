package com.mtg.mypbz2.view.editActivities

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import com.mtg.mypbz2.R
import com.mtg.mypbz2.presenter.DBHelper

class SearchEditActivity : AppCompatActivity() {
    companion object{
        const val ID = "id"
    }

    private lateinit var recyclerView: ListView

    private lateinit var db: SQLiteDatabase
    private lateinit var databaseHelper: DBHelper
    private lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_edit)
        val arguments = intent.extras
        recyclerView = findViewById(R.id.recview)
        databaseHelper = DBHelper(applicationContext)
        db = databaseHelper.readableDatabase
        val arg = arguments?.getInt(AddInspectionActivity.CAR_POLICE)
        when(arg) {
            AddInspectionActivity.CAR -> {
                val headers = arrayOf(
                    DBHelper.CAR_NUMBER, DBHelper.CAR_COLOR, DBHelper.CAR_MARK,
                        DBHelper.CAR_DRIVER_FIO, DBHelper.CAR_DRIVER_LICENSE)
                cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT1}", null)
                val userAdapter = SimpleCursorAdapter(this, R.layout.car_example, cursor, headers,
                        intArrayOf(
                            R.id.car_number,
                            R.id.car_color,
                            R.id.car_mark,
                            R.id.car_FIO,
                            R.id.car_driver_license
                        ), 0)
                recyclerView.adapter = userAdapter
                recyclerView.setOnItemClickListener { _, _, _, l ->
                    val intent = Intent(this, AddInspectionActivity::class.java)
                    intent.putExtra(ID, l.toInt())
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            AddInspectionActivity.POLICE -> {
                val headers = arrayOf(DBHelper.MENT_FIO, DBHelper.MENT_POSITION, DBHelper.MENT_RANK)
                cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT2}", null)
                val userAdapter = SimpleCursorAdapter(this,
                    R.layout.police_example, cursor, headers, intArrayOf(
                        R.id.ment_fio,
                        R.id.ment_position, R.id.ment_rank
                    ), 0)
                recyclerView.adapter = userAdapter
                recyclerView.setOnItemClickListener { _, _, _, l ->
                    val intent = Intent(this, AddInspectionActivity::class.java)
                    intent.putExtra(ID, l.toInt())
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            20 -> {
                val headers = arrayOf(
                    DBHelper.CAR_NUMBER, DBHelper.CAR_COLOR, DBHelper.CAR_MARK,
                        DBHelper.CAR_DRIVER_FIO, DBHelper.CAR_DRIVER_LICENSE)
                cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT1}", null)
                val userAdapter = SimpleCursorAdapter(this, R.layout.car_example, cursor, headers,
                        intArrayOf(
                            R.id.car_number,
                            R.id.car_color,
                            R.id.car_mark,
                            R.id.car_FIO,
                            R.id.car_driver_license
                        ), 0)
                recyclerView.adapter = userAdapter
                recyclerView.setOnItemClickListener { _, _, _, l ->
                    val intent = Intent(this, AddCarActivity::class.java)
                    intent.putExtra(ID, l.toInt())
                    startActivity(intent)
                    finish()
                }
            }
            21 -> {
                val headers = arrayOf(DBHelper.MENT_FIO, DBHelper.MENT_POSITION, DBHelper.MENT_RANK)
                cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT2}", null)
                val userAdapter = SimpleCursorAdapter(this,
                    R.layout.police_example, cursor, headers, intArrayOf(
                        R.id.ment_fio,
                        R.id.ment_position, R.id.ment_rank
                    ), 0)
                recyclerView.adapter = userAdapter
                recyclerView.setOnItemClickListener { _, _, _, l ->
                    val intent = Intent(this, AddInspectionActivity::class.java)
                    intent.putExtra(ID, l.toInt())
                    startActivity(intent)
                    finish()
                }
            }
            22 -> {
                val headers = arrayOf(DBHelper.TL_DATE, DBHelper.TL_MENT_FIO, DBHelper.TL_CAR_NUMBER, DBHelper.TL_RESULT)
                cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT3}", null)
                val userAdapter = SimpleCursorAdapter(this,
                    R.layout.inspection_example, cursor, headers, intArrayOf(
                        R.id.tl_date,
                        R.id.tl_ment_fio, R.id.tl_car_number, R.id.tl_result
                    ), 0)
                recyclerView.adapter = userAdapter
                recyclerView.setOnItemClickListener { _, _, _, l ->
                    val intent = Intent(this, AddInspectionActivity::class.java)
                    intent.putExtra(ID, l.toInt())
                    startActivity(intent)
                    finish()
                }
            }
            30 -> {
                val headers = arrayOf(
                    DBHelper.CAR_NUMBER, DBHelper.CAR_COLOR, DBHelper.CAR_MARK,
                        DBHelper.CAR_DRIVER_FIO, DBHelper.CAR_DRIVER_LICENSE)
                cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT1}", null)
                val userAdapter = SimpleCursorAdapter(this, R.layout.car_example, cursor, headers,
                        intArrayOf(
                            R.id.car_number,
                            R.id.car_color,
                            R.id.car_mark,
                            R.id.car_FIO,
                            R.id.car_driver_license
                        ), 0)
                recyclerView.adapter = userAdapter
                recyclerView.setOnItemClickListener { _, view, _, l ->
                    db.delete(DBHelper.TABLE_CONTACT1, "_id = ?", arrayOf(l.toString()))
                    userAdapter.notifyDataSetChanged()
                }
            }
            31 -> {
                val headers = arrayOf(DBHelper.MENT_FIO, DBHelper.MENT_POSITION, DBHelper.MENT_RANK)
                cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT2}", null)
                val userAdapter = SimpleCursorAdapter(this,
                    R.layout.police_example, cursor, headers, intArrayOf(
                        R.id.ment_fio,
                        R.id.ment_position, R.id.ment_rank
                    ), 0)
                recyclerView.adapter = userAdapter
                recyclerView.setOnItemClickListener { _, _, _, l ->
                    db.delete(DBHelper.TABLE_CONTACT2, "_id = ?", arrayOf(l.toString()))
                    userAdapter.notifyDataSetChanged()
                }
            }
            32 -> {
                val headers = arrayOf(DBHelper.TL_DATE, DBHelper.TL_MENT_FIO, DBHelper.TL_CAR_NUMBER, DBHelper.TL_RESULT)
                cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_CONTACT3}", null)
                val userAdapter = SimpleCursorAdapter(this,
                    R.layout.inspection_example, cursor, headers, intArrayOf(
                        R.id.tl_date,
                        R.id.tl_ment_fio, R.id.tl_car_number, R.id.tl_result
                    ), 0)
                recyclerView.adapter = userAdapter
                recyclerView.setOnItemClickListener { _, _, _, l ->
                    db.delete(DBHelper.TABLE_CONTACT3, "_id = ?", arrayOf(l.toString()))
                    userAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}