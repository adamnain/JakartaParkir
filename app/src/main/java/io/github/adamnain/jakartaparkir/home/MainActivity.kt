package io.github.adamnain.jakartaparkir.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.adamnain.jakartaparkir.R
import io.github.adamnain.jakartaparkir.R.id.*
import io.github.adamnain.jakartaparkir.about.AboutFragment
import io.github.adamnain.jakartaparkir.favorite.FavoriteFragment
import io.github.adamnain.jakartaparkir.listparkir.ListParkirFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                navigation_home -> {
                    loadListParkir(savedInstanceState)
                }
                navigation_favorite -> {
                    loadListFavorite(savedInstanceState)
                }
                navigation_about -> {
                    loadAboutFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = navigation_home
    }

    private fun loadListParkir(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, ListParkirFragment(), ListParkirFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadListFavorite(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadAboutFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, AboutFragment(), AboutFragment::class.java.simpleName)
                .commit()
        }
    }
}
