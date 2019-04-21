package com.example.locaui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.EditText
import com.example.locaui.R
import com.example.locaui.main.MainApp
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.activity_listview.*
import kotlinx.android.synthetic.main.cardview_det.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult


class ListView : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var wTitle: EditText? = null
    var wScroll: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.locaui.R.layout.activity_listview)
        app = application as MainApp
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
        val layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager
        myRecyclerView.adapter = WMAdapter(app.webMarks)

        wTitle = findViewById(R.id.webTitle)
        wScroll = findViewById(R.id.urlScroll)


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
        val view: View =
            (LayoutInflater.from(parent.context).inflate(com.example.locaui.R.layout.cardview_det, parent, false))
        return MainHolder(view)

    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        var webmark = webMarks[holder.adapterPosition]
        holder.bind(webmark)
        //  make onBindViewHolder(CourseViewHolder CourseViewHolder, final int i)
        holder.itemView.btnDeleteC.setOnClickListener {
            removeItem(position)
        }
        var titleOld = holder.itemView.webTitle.text
        var urlOld = holder.itemView.urlScroll.text
        holder.itemView.btnEditC.setOnClickListener {
           k->
            copyWebmarks(webmark)
            val intent = Intent(k.context, MainActivity::class.java)
            intent.putExtra("webTitle2", webmark.webName)
            intent.putExtra("urlScroll2", webmark.webUrl)
            k.context.startActivity(intent)



            Log.d("t1", "message ${webmark} ")
            Log.d("t2", "message2 " + getItem(position))
            Log.d("t3", "message: " + read())


        }
    }
    fun copyWebmarks(webmark: WebMarkModel): WebMarkModel {
        val result = WebMarkModel()
        result.webName = webmark.webName
        result.webUrl = webmark.webUrl

        return result
    }

    private fun read(): ArrayList<WebMarkModel> {
        return webMarks
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
   // fun iterative(): ArrayList<WebMarkModel> {
      //  return players.mapIndexed { i,  ->
           // if (i == 2) player.copy(score = 100)
           // else player
     //   }
   // }
   //fun <E> Iterable<E>.replace(old: E, new: E) = map { if (it == old) new else it }

    inner class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(webmark: WebMarkModel) {

            itemView.webTitle.text = webmark.webName
            itemView.urlScroll.text = webmark.webUrl



        }
    }

}