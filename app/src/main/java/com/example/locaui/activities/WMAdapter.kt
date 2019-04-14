package com.example.locaui.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.locaui.R
import com.example.locaui.model.WebMarkModel
import kotlinx.android.synthetic.main.activity_add.view.*


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
            //webMark.webName = itemView.addText1.toString()
            //webMark.webUrl = itemView.editText2.toString()
        }
    }
}