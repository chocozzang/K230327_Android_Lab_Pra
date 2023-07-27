package com.example.test10_12_jjh.test12

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context) : SQLiteOpenHelper
    (context, DBNAME, null, 1){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table " + TBLNAME +
        "(MID INTEGER PRIMARY KEY AUTOINCREMENT, ID TEXT, PW TEXT, NAME TEXT, ADDRESS TEXT, PHONE TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion : Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TBLNAME)
        onCreate(db)
    }

    val allData : Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("select * from $TBLNAME", null)
        }

    fun findMember(id: String?, pw: String?) : Cursor {
        val db = this.writableDatabase
        val myid = id!!
        val mypw = pw!!
        return db.rawQuery("select * from $TBLNAME WHERE ID = '$myid' AND PW = '$mypw'", null)
    }

    fun insertMember(id: String?, pw: String?, name: String?, address: String?, phone: String?) : Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, id)
        contentValues.put(COL_3, pw)
        contentValues.put(COL_4, name)
        contentValues.put(COL_5, address)
        contentValues.put(COL_6, phone)
        val result = db.insert(TBLNAME, null, contentValues)
        return result != -1L
    }

    fun deleteMember(mid : String) : Int {
        val db = this.writableDatabase
        return db.delete(TBLNAME, "MID = ?", arrayOf(mid))
    }

    fun updateMember(mid: String, id: String?, pw: String?, name: String?, address: String?, phone: String?) : Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, mid)
        contentValues.put(COL_2, id)
        contentValues.put(COL_3, pw)
        contentValues.put(COL_4, name)
        contentValues.put(COL_5, address)
        contentValues.put(COL_6, phone)
        db.update(TBLNAME, contentValues, "MID = ?", arrayOf(mid))
        return true
    }

    companion object {
        // DatabaseHelper 클래스명 -> mydb
        // mydb.DATABASE_NAME -> 사용가능.
        const val DBNAME = "MEMBER.db" // 데이터베이스 명
        const val TBLNAME = "tbl_member" // 테이블 명

        // 테이블 항목
        const val COL_1 = "MID"
        const val COL_2 = "ID"
        const val COL_3 = "PW"
        const val COL_4 = "NAME"
        const val COL_5 = "Address"
        const val COL_6 = "Phone"
    }
}

