package com.example.mim

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class adapterTabHome(fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeMainFragment.newInstance("1","1")
            1 -> HomeMenFragment.newInstance("2","2")
            2 -> HomeWomenFragment.newInstance("3","3")
            3 -> HomeKitFragment.newInstance("4","4")
            else -> null
        }!!
    }

    override fun getCount(): Int {
        return totalTabs
    }
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}