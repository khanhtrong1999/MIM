package com.example.mim

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mim.rooom.Word
import com.google.android.material.bottomsheet.BottomSheetDialog

class adapterjjjjjjj(val list: ArrayList<Word>, val context: Context, val l:`innn`): RecyclerView.Adapter<adapterjjjjjjj.ViewHolder>() {
    class ViewHolder(v: View):RecyclerView.ViewHolder(v) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return adapterjjjjjjj.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var obj = list[position]

        holder.itemView.findViewById<LinearLayout>(R.id.linearLayout4).setOnClickListener {
            l.onClick(obj)
        }
        holder.itemView.findViewById<ImageView>(R.id.giam).setOnClickListener {
            l.onGiam(obj)
        }
        holder.itemView.findViewById<ImageView>(R.id.tang).setOnClickListener {
            l.onTang(obj)
        }
        holder.itemView.findViewById<TextView>(R.id.textView17).text = obj.name
        val ss = obj.price * obj.soluong
        holder.itemView.findViewById<TextView>(R.id.price).text = ss.toString()
        holder.itemView.findViewById<TextView>(R.id.soluong).text = obj.soluong.toString()
        holder.itemView.findViewById<TextView>(R.id.color).text = obj.color
        holder.itemView.findViewById<TextView>(R.id.size).text = obj.size

//        System.out.println("http://192.168.1.122"+ obj.image)
        Glide.with(context).load("http://192.168.1.122"+obj.image).into(holder.itemView.findViewById<ImageView>(R.id.imageView2))
    }

    fun h(data: List<Word>){
        list.apply {
            clear()
            addAll(data)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }


}