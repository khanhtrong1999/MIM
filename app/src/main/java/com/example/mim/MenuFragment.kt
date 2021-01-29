   package com.example.mim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MenuFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_menu, container, false)
        val tabLayout: TabLayout = view.findViewById(R.id.tab)
//        tabLayout.addTab(tabLayout.newTab().setText("MEN"));
//        tabLayout.addTab(tabLayout.newTab().setText("WOMEN"));
//        tabLayout.addTab(tabLayout.newTab().setText("KIT"));
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val viewPager = view.findViewById<ViewPager>(R.id.view)
        val adapter = adapterTabMenu(childFragmentManager, tabLayout!!.tabCount)
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
        return view
    }
}