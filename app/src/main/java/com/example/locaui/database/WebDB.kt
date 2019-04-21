package com.example.locaui.database

import android.content.*
import android.database.sqlite.*
import com.example.locaui.model.WebMarkModel
import java.util.*

class WebDB(context: Context) : SQLiteOpenHelper(context, dbName, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val create_table_query =(
            "create table " + tableName + " ," + idTuple + " id_generator" +
                    name + " website name, " + url + " website url, ")
                    db!!.execSQL(create_table_query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists $tableName")
        onCreate(db!!)

    }




    fun findAll(): List<WebMarkModel>? {
        try {
            val listWM = ArrayList<WebMarkModel>()
            val database = this.writableDatabase
            val cursor = database.rawQuery("select * from $tableName", null)
            if (cursor.moveToFirst()) {
                do {
                    val wh = WebMarkModel()

                    wh.id = cursor.getInt(0)
                    wh.webName = cursor.getString(1)
                    wh.webUrl = cursor.getString(2)

                    listWM.add(wh)
                } while (cursor.moveToNext())
            }
            database.close()
            return listWM
        } catch (e: Exception) {
            return null
        }

    }

    fun createWM(ws: WebMarkModel): Boolean {
        try {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(idTuple, ws.id)
            contentValues.put(name, ws.webName)
            contentValues.put(url, ws.webUrl)
            val rows = db.insert(name, null, contentValues)
            db.close()
            return rows > 0
        } catch (e: Exception) {
            return false
        }

    }

    fun updateWM(ws: WebMarkModel): Int {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(idTuple, ws.id)
        values.put(name, ws.webName)
        values.put(url, ws.webUrl)

        return db.update(tableName,values,"$idTuple=?", arrayOf(ws.id.toString()))
    }

    fun deleteWM(ws: WebMarkModel) {

        val db = this.writableDatabase


        db.delete(tableName, "$idTuple=?", arrayOf(ws.id.toString()))
        db.close()
    }

    companion object {

        private val dbName = "WebDB"
        private val tableName = "WebMarks"
        private val idTuple = "ID"
        private val name = "Website Name"
        private val url = "Website URL"

    }
}
