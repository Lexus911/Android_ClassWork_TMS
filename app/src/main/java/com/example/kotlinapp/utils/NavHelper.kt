package com.example.kotlinapp.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController


object NavHelper {


    fun Fragment.navigate(destinationId: Int){
        findNavController().navigate(destinationId)
    }

    fun Fragment.replaceGraph(graphId: Int){
        findNavController().setGraph(graphId)
    }

    fun Fragment.navigateWithDeletedBackStack(destinationId: Int, fragmentToRemove: Int){
        val navOptions = NavOptions.Builder()
        navOptions.setPopUpTo(fragmentToRemove, true)
        findNavController().navigate(destinationId, null, navOptions.build())
    }

    fun Fragment.navigateWithBundle(destinationId: Int, bundle: Bundle){
        findNavController().navigate(destinationId, bundle)
    }


}