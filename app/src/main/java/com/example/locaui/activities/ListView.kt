package com.example.locaui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.locaui.R
import com.example.locaui.main.MainApp
import com.example.locaui.model.WebMarkModel

import kotlinx.android.synthetic.main.activity_listview.*
import kotlinx.android.synthetic.main.activity_listview.*
import org.jetbrains.anko.startActivityForResult

class ListView : AppCompatActivity() {

    var webMark = WebMarkModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)
        app = application as MainApp
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
        val layoutManager = LinearLayoutManager(this)
        val view = findViewById<RecyclerView>(R.id.myRecyclerView)

        view.layoutManager = layoutManager
        view.adapter = WMAdapter(app.webMarks)

    }
    //val webMarks = ArrayList<WebMarkModel>()
    //webMarks.add(WebMarkModel(webName = "supergamez", webUrl = "www.sg.hu"))


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.main_add -> startActivityForResult<MainActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }


}


//inner adapter class which is used to show the cardview

class WMAdapter constructor(private var webMarks: List<WebMarkModel>) :
    RecyclerView.Adapter<WMAdapter.MainHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val webmark = webMarks[holder.adapterPosition]
        holder.bind(webmark)
    }

    override fun getItemCount(): Int = webMarks.size

    inner class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(webmark: WebMarkModel) {


        }
    }
}
