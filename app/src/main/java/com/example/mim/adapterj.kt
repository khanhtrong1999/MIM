package com.example.mim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterj(var data: List<String>, var l:`in`): RecyclerView.Adapter<adapterj.ViewHolder>() {
    class ViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_textview,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = data[position]
        holder.itemView.findViewById<TextView>(R.id.textView15).text = obj
        holder.itemView.findViewById<TextView>(R.id.textView15).setOnClickListener {
            l.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}