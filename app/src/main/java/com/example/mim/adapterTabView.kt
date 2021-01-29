package com.example.mim

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
class adapterTabView(fm: FragmentManager, var image:List<String>): FragmentPagerAdapter(fm){

    private val NUM_PAGES = 3
    override fun getCount(): Int = NUM_PAGES

    override fun getItem(position: Int): Fragment{
       return when(position){
            0 -> ImageFragment.newInstance(image[0],"0")
            1 -> ImageFragment.newInstance(image[1],"1")
            2 -> ImageFragment.newInstance(image[2],"2")
            else -> null
        }!!
    }
}