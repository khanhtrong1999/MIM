package com.example.mim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterjj(var data: ArrayList<Category.datas>, var l:`in`): RecyclerView.Adapter<adapterjj.ViewHolder>() {
    class ViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = data[position]
        holder.itemView.findViewById<TextView>(R.id.id_name).text = obj.name
        holder.itemView.findViewById<TextView>(R.id.id_name).setOnClickListener {
            l.onClick(obj.id)
        }
        holder.itemView.findViewById<TextView>(R.id.all).setOnClickListener { 
            l.onClick(obj.id)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun addDatas(users: List<Category.datas>) {
        this.data.apply {
            clear()
            addAll(users)
        }
    }
}