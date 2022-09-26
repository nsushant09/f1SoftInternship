package com.neupanesushant.learnviewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm : FragmentManager, lifecycle : Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return FirstFragment()
            1 -> return SecondFragment()
        }
        return ThirdFragment()
    }
}