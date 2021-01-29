package com.example.mim

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class adapterk(val list: ArrayList<Order.obj2>, val context: Context, val l:`in`): RecyclerView.Adapter<adapterk.ViewHolder>() {
    class ViewHolder(v: View):RecyclerView.ViewHolder(v) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return adapterk.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order_all, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var obj = list[position]

        holder.itemView.findViewById<LinearLayout>(R.id.linearLayout4).setOnClickListener {
            l.onClick(1)
        }
        holder.itemView.findViewById<TextView>(R.id.textView17).text = obj.name
        holder.itemView.findViewById<TextView>(R.id.price).text = obj.price.toString()
        holder.itemView.findViewById<TextView>(R.id.soluong).text = obj.qty.toString()


    }

    fun addDatas(data: List<Order.obj2>){
        list.apply {
            clear()
            addAll(data)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }


}