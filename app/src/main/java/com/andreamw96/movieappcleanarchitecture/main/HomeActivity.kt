package com.andreamw96.movieappcleanarchitecture.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.andreamw96.movieappcleanarchitecture.R
import com.andreamw96.movieappcleanarchitecture.databinding.ActivityHomeBinding
import com.andreamw96.movieappcleanarchitecture.main.moviefragment.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

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
                installFavoriteModule()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun installFavoriteModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleFavorite = "favorite"
        if (splitInstallManager.installedModules.contains(moduleFavorite)) {
            showFavoriteFragment()
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleFavorite)
                .build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    showFavoriteFragment()
                }
                .addOnFailureListener {
                    showSnackbarModuleNotInstalled()
                }
        }
    }

    private fun showFavoriteFragment() {
        val favoriteFragment = Class.forName("com.andreamw96.favorite.FavoriteFragment").newInstance() as Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_layout, favoriteFragment, favoriteFragment.javaClass.simpleName)
            .commit()
    }

    private fun showSnackbarModuleNotInstalled() {
        Snackbar.make(binding.container, "Module Favorite Not Installed", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                installFavoriteModule()
            }.show()
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