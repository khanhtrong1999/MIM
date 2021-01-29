package com.example.mim


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class adapterjjj(val data: ArrayList<obj1.obj2>, var l:`in`, var context: Context): RecyclerView.Adapter<adapterjjj.ViewHolder>() {

    class ViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.itemView.findViewById<TextView>(R.id.textMovieTitle).text = item.name
        holder.itemView.findViewById<TextView>(R.id.textMovieViews).text = item.price.toString()
        Glide.with(context).load("http://192.168.1.122"+item.image[0]).into(holder.itemView.findViewById<ImageView>(R.id.imageMovie))
        holder.itemView.findViewById<ConstraintLayout>(R.id.constraint).setOnClickListener {
            l.onClick(item.id)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addDatas(users: List<obj1.obj2>) {
        this.data.apply {
            clear()
            addAll(users)
        }
    }

}