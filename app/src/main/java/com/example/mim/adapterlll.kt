package com.example.mim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterlll(val data:ArrayList<Color.datas>): RecyclerView.Adapter<adapterlll.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterlll.ViewHolder {
        return adapterlll.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_color_filter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: adapterlll.ViewHolder, position: Int) {
        val obj = data[position]
        System.out.println(obj.name)
        holder.itemView.findViewById<TextView>(R.id.view).text = obj.name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addDatas(users: List<Color.datas>) {
        this.data.apply {
            clear()
            addAll(users)
        }
    }
}