package com.example.locaui.database

import android.content.*
import android.database.sqlite.*
import android.widget.Toast
import com.example.locaui.model.WebMarkModel
import java.util.*

class WebDB(var context: Context) : SQLiteOpenHelper(context, DBNAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + TABLE_NAME + " ," +
                    ID_TUPLE + " ID GENERATOR, " +
                    NAME + " WEBSITE NAME, " +
                    URL + " URL, "

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists $TABLE_NAME ")
        onCreate(db!!)

    }




    fun findAll(): List<WebMarkModel>? {
        try {
            val listWM = ArrayList<WebMarkModel>()
            val database = this.writableDatabase
            val cursor = database.rawQuery("select * from $TABLE_NAME", null)
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

    fun createWM(ws: WebMarkModel) {

            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(ID_TUPLE, ws.id)
            contentValues.put(NAME, ws.webName)
            contentValues.put(URL, ws.webUrl)
            val rows = db.insert(NAME, null, contentValues)
            if(rows == -1.toLong())
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()


    }

    fun updateWM(ws: WebMarkModel): Int {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID_TUPLE, ws.id)
        values.put(NAME, ws.webName)
        values.put(URL, ws.webUrl)

        return db.update(TABLE_NAME,values,"$ID_TUPLE=?", arrayOf(ws.id.toString()))
    }

    fun deleteWM(ws: WebMarkModel) {

        val db = this.writableDatabase


        db.delete(TABLE_NAME, "$ID_TUPLE=?", arrayOf(ws.id.toString()))
        db.close()
    }

    companion object {

        private val DBNAME = "WebDB"
        private val TABLE_NAME = "WebMarks"
        private val ID_TUPLE = "ID"
        private val NAME = "Website Name"
        private val URL = "Website URL"

    }
}
