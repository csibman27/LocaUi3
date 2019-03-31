package com.example.locaui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.locaui.R
import com.example.locaui.main.MainApp
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import java.util.*

class MainActivity : AppCompatActivity(), AnkoLogger {


    var webMark = WebMarkModel()
    var app: MainApp? = null
    val webMarks = ArrayList<WebMarkModel>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp


        val myTxt = findViewById<TextView>(R.id.textView1)
        myTxt.text = "   List of Websites is currently empty"




            fAddBtn.setOnClickListener() {
                val intent = Intent(this, AddActivity :: class.java)
                startActivity(intent)
            //Toast.makeText(this, "Entered to Add Websites", Toast.LENGTH_LONG).show();


        }
    }
}

