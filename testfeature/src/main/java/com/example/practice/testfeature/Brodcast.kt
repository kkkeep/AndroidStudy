package com.example.practice.testfeature

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

/**
 * Created by taofu
 *on 2018/4/16.
 */

class MyBroadcastReceiver : BroadcastReceiver(){
    var b: Boolean = false
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.i("Test","onReceive")
        if(intent == null){
            return
        }

        if(intent.action === ConnectivityManager.CONNECTIVITY_ACTION){

            if(b){
                context?.startService(Intent(context,BackgroundService::class.java))
            }

            b = true
        }
    }
}