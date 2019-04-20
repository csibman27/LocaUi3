package com.example.locaui.main

import android.app.Application
import com.example.locaui.model.WebMarkModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp: Application(), AnkoLogger {

    val webMarks = ArrayList<WebMarkModel>()


    override fun onCreate() {
        super.onCreate()
        info("this is webmarks")
        webMarks.run {
            add(WebMarkModel("Supergamez","www.sg.hu"))
            add(WebMarkModel("Example", "www.example.com"))
        }

    }
}