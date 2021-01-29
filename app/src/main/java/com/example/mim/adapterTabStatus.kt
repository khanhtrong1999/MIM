package com.example.mim

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class adapterTabStatus(fm: FragmentManager, var totalTabs: Int, var id:Int): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AllStatusFragment.newInstance(id,"0")
            1 -> PendingStatusFragment.newInstance(id,"1")
            2 -> VerifiedStatusFragment.newInstance(id,"2")
            3 -> ShippedStatusFragment.newInstance(id,"3")
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