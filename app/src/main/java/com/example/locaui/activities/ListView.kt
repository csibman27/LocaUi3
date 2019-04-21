package com.example.locaui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.example.locaui.main.MainApp
import kotlinx.android.synthetic.main.activity_listview.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult


class ListView : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.locaui.R.layout.activity_listview)
        app = application as MainApp
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
        val layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager
        myRecyclerView.adapter = WMAdapter(app.webMarks, webID, webTitle, urlScroll)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.locaui.R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            com.example.locaui.R.id.main_add -> startActivityForResult<MainActivity>(0)
        }
        return super.onOptionsItemSelected(item)

    }
}

//inner adapter class which is used to show the cardview

