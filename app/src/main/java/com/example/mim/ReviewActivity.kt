package com.example.mim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val recyc = findViewById<RecyclerView>(R.id.kkkk)
        val list = arrayListOf<String>(
            "ORDER",
            "CHANGE PROFILE",
            "CHANGE PASSWORD",
            "LOGOUT",
        )
        val adapter = adapterReview(list, this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyc!!.layoutManager = layoutManager
        layoutManager.setAutoMeasureEnabled(true)
        recyc!!.adapter = adapter
    }
}