package com.anynews.myapplication.navigations

import android.os.Bundle

interface Navigations {
    fun navigate(graph: NavGraph, args: Bundle? = null)

    fun popBackStack()
}