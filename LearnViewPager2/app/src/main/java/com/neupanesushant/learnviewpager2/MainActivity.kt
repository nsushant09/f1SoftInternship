package com.neupanesushant.learnviewpager2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.neupanesushant.learnviewpager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titleList = listOf("First Fragment", "Second Fragment", "Third Fragment")

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = titleList.get(position)
//            tab.view.setBackgroundColor(R.color.black)
            tab.parent?.setTabTextColors(ContextCompat.getColor(applicationContext,
                com.google.android.material.R.color.material_blue_grey_800), ContextCompat.getColor(applicationContext,R.color.test_color))
            tab.parent?.setSelectedTabIndicatorColor(ContextCompat.getColor(applicationContext, R.color.test_color))
            tab.parent?.setTabRippleColorResource(R.color.white)
        }.attach()
    }
}