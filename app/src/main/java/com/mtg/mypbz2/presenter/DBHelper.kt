package com.mtg.mypbz2.presenter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_CONTACT1 ($CAR_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $CAR_NUMBER TEXT," +
                " $CAR_ENGINE_NUMBER INTEGER," +
                " $CAR_COLOR TEXT," +
                " $CAR_MARK TEXT," +
                " $CAR_PASSPORT INTEGER," +
                " $CAR_DRIVER_LICENSE INTEGER," +
                " $CAR_DRIVER_FIO TEXT," +
                " $CAR_DRIVER_ADDRESS TEXT," +
                " $CAR_DRIVER_YEAR INTEGER," +
                " $CAR_DRIVER_SEX TEXT);")

        p0?.execSQL("CREATE TABLE $TABLE_CONTACT2($MENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$MENT_FIO TEXT, " +
                "$MENT_POSITION TEXT, " +
                "$MENT_RANK TEXT);")

        p0?.execSQL("CREATE TABLE $TABLE_CONTACT3($TL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$TL_CAR_ID INTEGER, " +
                "$TL_CAR_NUMBER TEXT, " +
                "$TL_MENT_ID INTEGER, " +
                "$TL_MENT_FIO TEXT, " +
                "$TL_DATE TEXT," +
                "$TL_RESULT TEXT, " +
                "$TL_CONCLUSION TEXT);")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE $TABLE_CONTACT1")
        p0?.execSQL("DROP TABLE $TABLE_CONTACT2")
        p0?.execSQL("DROP TABLE $TABLE_CONTACT3")
        onCreate(p0)
    }
    companion object{
        var DATABASE_VERSION = 1
        var DATABASE_NAME = "gibdd.db"
        var TABLE_CONTACT1 = "CARS"
        var TABLE_CONTACT2 = "POLICE"
        var TABLE_CONTACT3 = "TL"

        var CAR_ID = "_id"
        var CAR_NUMBER = "number"
        var CAR_ENGINE_NUMBER = "engine_number"
        var CAR_COLOR = "color"
        var CAR_MARK = "mark"
        var CAR_PASSPORT = "passport"
        var CAR_DRIVER_LICENSE = "driver_license"
        var CAR_DRIVER_FIO = "driver_fio"
        var CAR_DRIVER_ADDRESS = "address"
        var CAR_DRIVER_YEAR = "driver_year"
        var CAR_DRIVER_SEX = "sex"

        var TL_ID = "_id"
        var TL_CAR_ID = "car_id"
        var TL_CAR_NUMBER = "car_number"
        var TL_MENT_ID = "ment_id"
        var TL_MENT_FIO = "ment_fio"
        var TL_DATE = "date"
        var TL_RESULT = "result"
        var TL_CONCLUSION = "conclusion"

        var MENT_ID = "_id"
        var MENT_FIO = "fio"
        var MENT_POSITION = "position"
        var MENT_RANK = "rank"
    }
}