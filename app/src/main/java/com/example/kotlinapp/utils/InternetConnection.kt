package com.example.kotlinapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import javax.inject.Inject

class InternetConnection @Inject constructor(
   private val context: Context
) {

   @RequiresApi(Build.VERSION_CODES.M)
   fun isOnline(): Boolean {
       val connectivityManager =
           context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
       if (connectivityManager != null) {
           val capabilities =
               connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
           if (capabilities != null) {
               if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                   Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                   return true
               } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                   Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                   return true
               } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                   Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                   return true
               }
           }
       }
       return false
   }
}
