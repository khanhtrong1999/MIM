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

class adapterReview(val data: ArrayList<String>, var context: Context): RecyclerView.Adapter<adapterReview.ViewHolder>() {

    class ViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addDatas(users: ArrayList<String>) {
        this.data.apply {
            clear()
            addAll(users)
        }
    }

}