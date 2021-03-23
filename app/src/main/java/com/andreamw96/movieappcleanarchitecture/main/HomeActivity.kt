package com.andreamw96.movieappcleanarchitecture.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.andreamw96.movieappcleanarchitecture.R
import com.andreamw96.movieappcleanarchitecture.databinding.ActivityHomeBinding
import com.andreamw96.movieappcleanarchitecture.main.moviefragment.MainFragment
import com.andreamw96.movieappcleanarchitecture.utils.FavoriteModuleUtils.installFavoriteModule
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment

        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = MainFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_favorite -> {
                installFavoriteModule(this, this, binding.root)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.navigation_home
        }
    }
}