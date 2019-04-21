package com.example.locaui.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.example.locaui.R
import com.example.locaui.main.MainApp
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_listview.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class EditA : AppCompatActivity(), AnkoLogger {

    val webMarks = ArrayList<WebMarkModel>()
    var webMark = WebMarkModel()
    var app: MainApp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setSupportActionBar(toolbarMain)
        app = application as MainApp


        btnAddE.setOnClickListener {
            webMark.webName = webTitle2.text.toString()
            webMark.webUrl = urlScroll2.text.toString()
            if (webMark.webName.isNotEmpty()) {
                app!!.webMarks.add(webMark.copy())
                info("Edit Button pressed: $webTitle2")
                app!!.webMarks.forEach { info("Add button pressed: ${it}") }
                setResult(RESULT_OK)
                finish()
            }
        }
        btnBackE.setOnClickListener {
            btnBack.setOnClickListener {
                val intent2 = Intent(this, MainActivity::class.java)
                startActivity(intent2)
            }
        }
    }
}


