package com.example.locaui.activities

import android.support.design.widget.TextInputEditText
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.cardview_det.view.*

class WMAdapter (
    private var webMarks: ArrayList<WebMarkModel>,
    var activity: TextInputEditText,
    var webID: EditText,
    var webTitle: EditText) :
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




            Log.d("t1", "message ${webmark} ")
            Log.d("t2", "message2 " + getItemID(position))
            Log.d("t3", "message: " + read())


        }
    }

    fun getItem(position: Int): Any {
        return webMarks[position]
    }

    private fun read(): ArrayList<WebMarkModel> {
        return webMarks
    }

    fun getItemID(position: Int): Long {

        return webMarks[position].id.toLong()
    }

    override fun getItemCount(): Int = webMarks.size

    fun removeItem(position: Int) {

        webMarks.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, webMarks.size)
    }


    inner class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(webmark: WebMarkModel) {

            itemView.webID.text = webmark.id.toString()
            itemView.webTitle.text = webmark.webName
            itemView.urlScroll.text = webmark.webUrl



        }
    }

}