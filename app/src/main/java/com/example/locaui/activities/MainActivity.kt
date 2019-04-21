package com.example.locaui.activities


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.locaui.R
import com.example.locaui.database.WebDB
import com.example.locaui.main.MainApp
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), AnkoLogger {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Toast.makeText(this, "Entered to Add Websites", Toast.LENGTH_LONG).show();
        btnAdd.setOnClickListener {
                    if (webTitle.text.toString().length > 0 &&
                            urlScroll.text.toString().length > 0) {
                            var webMarks = WebMarkModel(webTitle.text.toString(),webTitle.text.toString())
                            var db = WebDB(this)
                            db.createWM(webMarks)
                    }else {
                        Toast.makeText(this, "Please fill all data",Toast.LENGTH_SHORT)
                    }
        }



        fun onClickEdit(webMark: WebMarkModel) {
            /*RxBus.send(student)*/
            startActivity(Intent(this, ListView::class.java).putExtra("webTtile", webMark.webName))
        }

        fun clickDelete(webMarks: WebMarkModel) {
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
