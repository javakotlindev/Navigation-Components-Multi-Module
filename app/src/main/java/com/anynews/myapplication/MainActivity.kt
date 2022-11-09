package com.anynews.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.anynews.myapplication.navigations.NavGraph
import com.anynews.myapplication.navigations.Navigations

class MainActivity : AppCompatActivity(), Navigations {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

    }

    override fun navigate(graph: NavGraph, args: Bundle?) {
        when (graph) {
            NavGraph.LOGIN -> navController.navigate(R.id.action_registerFragment_to_loginFragment)
            NavGraph.REGISTER -> navController.navigate(R.id.action_loginFragment_to_registerFragment, args)
            NavGraph.MAIN -> navController.navigate(R.id.action_registerFragment_to_mainFragment)
        }
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}