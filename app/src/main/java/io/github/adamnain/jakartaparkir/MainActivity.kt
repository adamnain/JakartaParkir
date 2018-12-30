package io.github.adamnain.jakartaparkir

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import io.github.adamnain.jakartaparkir.R.id.*
import io.github.adamnain.jakartaparkir.listparkir.ListParkirFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                navigation_home -> {
                    loadLastFragment(savedInstanceState)
                }
                navigation_favorite -> {
                    loadLastFragment(savedInstanceState)
                }
                navigation_about -> {
                    loadLastFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = navigation_home
    }

    private fun loadLastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, ListParkirFragment(), ListParkirFragment::class.java.simpleName)
                .commit()
        }
    }
}
