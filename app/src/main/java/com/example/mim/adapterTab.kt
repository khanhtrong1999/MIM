package com.example.mim

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class adapterTab(fm: FragmentManager,var totalTabs: Int): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    @NonNull
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return MenuFragment()
            }
            1 -> {
                return MenuFragment()
            }
            2 -> {
                return MenuFragment()
            }
        }
        throw IllegalStateException("position $position is invalid for this viewpager")
    }
}