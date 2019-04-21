package com.example.locaui.activities

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.cardview_det.view.*

class WMAdapter (private var webMarks: ArrayList<WebMarkModel>) :
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
                holder?.itemView.webTitle.setText("Text Edited")




            Log.d("t1", "message ${webmark} ")
            Log.d("t3", "message: " + read())



        }
    }
    fun getValue() {

    }

    fun clear() {
         webMarks = ArrayList()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Any {
        return webMarks[position]
    }

    fun removeItem(webmark: WebMarkModel) {
        webMarks?.forEachIndexed {
                index, item ->
            if (item.id == webmark.id) {
                webMarks?.removeAt(index)
                notifyDataSetChanged()
                return@forEachIndexed
            }
        }
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

            itemView.webTitle.text = webmark.webName
            itemView.urlScroll.text = webmark.webUrl



        }
    }

}
