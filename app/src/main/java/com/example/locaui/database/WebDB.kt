package com.example.locaui.database

import android.content.*
import android.database.sqlite.*
import com.example.locaui.model.WebMarks
import java.util.*

class WebDB(context: Context) : SQLiteOpenHelper(context, dbName, null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + tableName + " ," + idTuple + " id_generator" +
                    name + " website name, " + url + " website url, " + description + " description, "
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists$tableName")
        onCreate(db)

    }

    fun findAll(): List<WebMarks>? {
        try {
            val webhandler = ArrayList<WebMarks>()
            val database = writableDatabase
            val cursor = database.rawQuery("select * from $tableName", null)
            if (cursor.moveToFirst()) {
                do {
                    val wh = WebMarks()

                    wh.webName = cursor.getString(0)
                    wh.webUrl = cursor.getString(1)

                    webhandler.add(wh)
                } while (cursor.moveToNext())
            }
            database.close()
            return webhandler
        } catch (e: Exception) {
            return null
        }

    }

    fun create(ws: WebMarks): Boolean {
        try {
            val db = writableDatabase
            val contentValues = ContentValues()
            contentValues.put(name, ws.webName)
            contentValues.put(url, ws.webUrl)
            val rows = db.insert(name, null, contentValues)
            db.close()
            return rows > 0
        } catch (e: Exception) {
            return false
        }

    }

    companion object {

        private val dbName = "WebDB"
        private val tableName = "WebMarks"
        private val idTuple = "ID"
        private val name = "Website Name"
        private val url = "Website URL"
        private val description = "Website Description"
    }
}
