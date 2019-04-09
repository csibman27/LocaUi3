package com.example.locaui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.locaui.R
import com.example.locaui.main.MainApp
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.cardview.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

class AddActivity : AppCompatActivity(), AnkoLogger {


    var webMark = WebMarkModel()
    var app: MainApp? = null
    val webMarks = ArrayList<WebMarkModel>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        app = application as MainApp


        val button2 = findViewById<Button>(R.id.btnBackAdd)
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val button1 = findViewById<Button>(R.id.btnSubmitAdd)
        button1.setOnClickListener {
            webMark.webName = textView1.text.toString().trim()
            if (webMark.webName.isNotEmpty()) {
                app!!.webMarks.add(webMark.copy())
                info("Add Button pressed: $addText1")
                app!!.webMarks.forEach { info("Add button pressed: ${it}") }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }


        }
        //class WebMarkAdapter constructor(private var webMarks: List<WebMarkModel>): RecyclerView.Adapter<WebMarkAdapter.MainHolder>() {

        }
    }

