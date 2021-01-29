package com.example.mim

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class StatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        val tabLayout: TabLayout = findViewById(R.id.tab)
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val sharedpreferences = getSharedPreferences(
            "APPMIMM",
            Context.MODE_PRIVATE
        )
        var id:Int = sharedpreferences.getInt("key", -1);
        System.out.println("cha mm"+id)
        val viewPager = findViewById<ViewPager>(R.id.viewPager2)
        val adapter = adapterTabStatus(supportFragmentManager, tabLayout!!.tabCount, id)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}