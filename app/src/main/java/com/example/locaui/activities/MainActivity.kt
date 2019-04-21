package com.example.locaui.activities


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.locaui.R
import com.example.locaui.main.MainApp
import com.example.locaui.model.WebMarkModel
import com.example.locaui.model.WebMarks
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), AnkoLogger {


    var webMark = WebMarkModel()
    var app: MainApp? = null
    val webMarks = ArrayList<WebMarkModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp


        // val myTxt = findViewById<TextView>(R.id.textView1)
        // myTxt.text = "   List of Websites is currently empty"


        //Toast.makeText(this, "Entered to Add Websites", Toast.LENGTH_LONG).show();
        btnAdd.setOnClickListener {
            if(webTitle.text!!.isEmpty()) {
                webTitle.error = "Please enter some title"
            }
            if(urlScroll.text!!.isEmpty()) {
                urlScroll.error = "Please enter any url"
            }
            webMark.webName = webTitle.text.toString()
            webMark.webUrl = urlScroll.text.toString()
            if (webMark.webName.isNotEmpty()) {
                app!!.webMarks.add(webMark.copy())
                info("Add Button pressed: $webTitle")
                app!!.webMarks.forEach { info("Add button pressed: ${it}") }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
        }

        fun clickDelete(webMarks: WebMarks) {
            val alertDialogBuilder by lazy { return@lazy AlertDialog.Builder(this) }
            alertDialogBuilder.setTitle("Delete")
            alertDialogBuilder.setMessage("Are you sure you want to delete this ${webMarks.webName}?")

        }

        btnBack.setOnClickListener {
            val intent = Intent(this, ListView::class.java)
            startActivity(intent)
        }

    }

}
interface Store {
    fun findAll(): List<WebMarkModel>
    fun create(webmark: WebMarkModel)
}
class  MemStore : Store {


    val webmarks = ArrayList<WebMarkModel>()

    override fun findAll(): List<WebMarkModel> {
        return webmarks
    }

    override fun create(webmark: WebMarkModel) {
        webmarks.add(webmark)
    }


}
