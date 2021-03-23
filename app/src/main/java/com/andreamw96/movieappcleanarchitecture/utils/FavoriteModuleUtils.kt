package com.andreamw96.movieappcleanarchitecture.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.andreamw96.movieappcleanarchitecture.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

object FavoriteModuleUtils {
    fun isFavoriteModuleInstalled(context: Context) : Boolean {
        val splitInstallManager = SplitInstallManagerFactory.create(context)
        val moduleFavorite = "favorite"
        return splitInstallManager.installedModules.contains(moduleFavorite)
    }

    fun installFavoriteModule(context: Context, activity: AppCompatActivity, view: View) {
        val splitInstallManager = SplitInstallManagerFactory.create(context)
        val moduleFavorite = "favorite"
        if (splitInstallManager.installedModules.contains(moduleFavorite)) {
            showFavoriteFragment(context, activity, view)
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleFavorite)
                .build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    showFavoriteFragment(context, activity, view)
                }
                .addOnFailureListener {
                    showSnackbarModuleNotInstalled(context, activity, view)
                }
        }
    }

    private fun showFavoriteFragment(context: Context, activity: AppCompatActivity, view: View) {
        val favoriteFragment = Class.forName("com.andreamw96.favorite.FavoriteFragment").newInstance() as Fragment
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container_layout, favoriteFragment, favoriteFragment.javaClass.simpleName)
            .commit()
    }

    private fun showSnackbarModuleNotInstalled(context: Context, activity: AppCompatActivity, view: View) {
        Snackbar.make(view, "Module Favorite Not Installed", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                installFavoriteModule(context, activity, view)
            }.show()
    }
}