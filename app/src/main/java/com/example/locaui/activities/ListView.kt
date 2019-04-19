package com.example.locaui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.locaui.R
import com.example.locaui.main.MainApp
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.activity_listview.*
import kotlinx.android.synthetic.main.cardview_det.view.*
import org.jetbrains.anko.startActivityForResult


class ListView : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.locaui.R.layout.activity_listview)
        app = application as MainApp
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
        val layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager
        myRecyclerView.adapter = WMAdapter(app.webMarks)

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

class WMAdapter constructor(private var webMarks: ArrayList<WebMarkModel>) :
    RecyclerView.Adapter<WMAdapter.MainHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view: View = (LayoutInflater.from(parent.context).inflate(com.example.locaui.R.layout.cardview_det, parent, false))
        return MainHolder(view)

    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val webmark = webMarks[holder.adapterPosition]
        holder.bind(webmark)
       //  make onBindViewHolder(CourseViewHolder CourseViewHolder, final int i)
        holder.itemView.btnDeleteC.setOnClickListener {
            removeItem(position)
        }
        holder.itemView.btnEditC.setOnClickListener {
                a->
                var intent = Intent(a.context, MainActivity::class.java)
                intent.putExtra("webTitle", webmark.webName)
                intent.putExtra("urlScroll", webmark.webUrl)
                a.context.startActivity(intent)
            }
            //val tar1 = webmark.webName.toString()
            //val idc = webMarks.indexOf(webmark)
            //if (idc.equals(webmark)) {
                //webMarks.get(position)

        }

    fun getItem(position: Int): WebMarkModel {

        return webMarks[position]
    }

    override fun getItemCount(): Int = webMarks.size

    fun removeItem(position: Int) {
        webMarks.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, webMarks.size)
    }
    fun editItem(webmark: WebMarkModel) {
       var foundWebMarks: WebMarkModel? = webMarks.find { p-> p.webName == webmark.webName }
        if (foundWebMarks != null) {
            foundWebMarks.webName = webmark.webName
            foundWebMarks.webUrl = webmark.webUrl
        }
    }


    fun clearItem() {
        webMarks = ArrayList()
        notifyDataSetChanged()
    }



    inner class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(webmark: WebMarkModel) {

            itemView.webTitle.text = webmark.webName
            itemView.urlScroll.text = webmark.webUrl



        }
    }

}
