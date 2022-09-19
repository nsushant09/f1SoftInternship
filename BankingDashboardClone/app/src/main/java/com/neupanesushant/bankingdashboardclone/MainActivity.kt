package com.neupanesushant.bankingdashboardclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.neupanesushant.bankingdashboardclone.databinding.ActivityMainBinding
import com.neupanesushant.bankingdashboardclone.fragments.Cards
import com.neupanesushant.bankingdashboardclone.fragments.Home
import com.neupanesushant.bankingdashboardclone.fragments.Reports
import com.neupanesushant.bankingdashboardclone.fragments.Settings

class MainActivity : AppCompatActivity() {

    private val homeFragment = Home()
    private val reportsFragment = Reports()
    private val cardsFragment = Cards()
    private val settingsFragment = Settings()

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentFragmentListener(binding.bottomNavigation)
        loadFirstTime()
    }

    fun loadFirstTime(){
        binding.bottomNavigation.selectedItemId = R.id.home
    }

    fun replaceFragment(fragment : Fragment){
        if(fragment!=null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val animation = AnimationUtils.loadAnimation(baseContext, androidx.appcompat.R.anim.abc_fade_in)
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.isAddToBackStackAllowed
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        showTransparentStatusBar()
    }

    fun currentFragmentListener(
        navigationBarView: NavigationBarView
    ){

        navigationBarView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.cards -> replaceFragment(cardsFragment)
                R.id.settings -> replaceFragment(settingsFragment)
                R.id.reports -> replaceFragment(reportsFragment)
            }
            true
        }
    }



    fun showTransparentStatusBar(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    fun hideTransparentStatusBar(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

}
