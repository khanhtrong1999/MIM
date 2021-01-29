package com.example.mim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterll(val data: ArrayList<Production.DataK.ColorAndSize>, var l: inn): RecyclerView.Adapter<adapterll.ViewHolder>() {
    private var selectedItem = 0
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterll.ViewHolder {
        return adapterll.ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        )
    }

    override fun onBindViewHolder(holder: adapterll.ViewHolder, position: Int) {
        val obj = data[position]
        holder.itemView.setSelected(selectedItem == position)
        System.out.println("BABABTHUONG")
        holder.itemView.findViewById<TextView>(R.id.view).apply {
            text = obj.name
            setOnClickListener {
                val previousItem: Int = selectedItem
                selectedItem = position

                notifyItemChanged(previousItem)
                notifyItemChanged(position)

                l.onClickColor(obj.name)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addDatas(users: List<Production.DataK.ColorAndSize>) {
        this.data.apply {
            clear()
            addAll(users)
        }
    }
}