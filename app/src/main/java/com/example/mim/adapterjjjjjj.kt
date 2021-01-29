package com.example.mim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterjjjjjj(val data: ArrayList<Production.DataK.ColorAndSize>, var l: inn): RecyclerView.Adapter<adapterjjjjjj.ViewHolder>() {
    private var selectedItem = 0
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterjjjjjj.ViewHolder {
        return adapterjjjjjj.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_size, parent, false))
    }

    override fun onBindViewHolder(holder: adapterjjjjjj.ViewHolder, position: Int) {
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

                l.onClickSize(obj.name)
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