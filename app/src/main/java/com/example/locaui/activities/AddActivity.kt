package com.example.locaui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.locaui.R
import com.example.locaui.main.MainApp
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cardview.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

class AddActivity : AppCompatActivity(), AnkoLogger {


    var webMark = WebMarkModel()
    var app: MainApp? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        app = application as MainApp

        val view = findViewById<RecyclerView>(R.id.myTxt)
        view.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL, false)


        val webMarks = ArrayList<WebMarkModel>()
        webMarks.add(WebMarkModel(webName = "supergamez",webUrl = "www.sg.hu"))


//        pass the values to RvAdapter
        val rvAdapter = WMAdapter(webMarks)
//        set the recyclerView to the adapter
        view.adapter = rvAdapter


        val button2 = findViewById<Button>(R.id.btnBackAdd)
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val button1 = findViewById<Button>(R.id.btnSubmitAdd)
        button1.setOnClickListener {
            webMark.webName = textView1.text.toString()
            if (webMark.webName.isNotEmpty()) {
                app!!.webMarks.add(webMark.copy())
                info("Add Button pressed: $addText1")
                app!!.webMarks.forEach { info("Add button pressed: ${it.webName}") }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }


        }

    }
}

