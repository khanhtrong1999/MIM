package com.example.mim

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class adapter( var data: List<Int>): RecyclerView.Adapter<adapter.ViewHolder>() {
    class ViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_circle_image,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = data[position]
        holder.itemView.findViewById<AppCompatImageView>(R.id.profile_img_post).setImageResource(obj)
//        Glide
//            .with(myFragment)
//            .load(url)
//            .centerCrop()
//            .placeholder(obj)
//            .into(myImageView);
    }

    override fun getItemCount(): Int {
        return data.size
    }
}