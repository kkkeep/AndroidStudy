package com.example.practice.testfeature

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v4.app.JobIntentService
import android.support.v4.app.NotificationCompat
import android.util.Log
import java.util.*

/**
 * Created by taofu
 *on 2018/4/16.
 */

class BackgroundService : Service(){

    val hander = @SuppressLint("HandlerLeak")
    object: Handler(){

        override fun handleMessage(msg: Message?) {
           Log.i("Test",Date().toString())

            sendEmptyMessageDelayed(0,1000)
        }
    }




    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onCreate() {
        super.onCreate()
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(this,"Service")
        builder.setContentTitle("TestTitle")
        builder.setContentText("TesTForegroundService")
       // startForeground(1,builder.build())
        hander.sendEmptyMessageDelayed(0,1000)

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("Test",this.javaClass.simpleName + " onDestroy")
    }
}


class OtherService : Service(){
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        Log.i("Test",this::class.java.simpleName  + "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("Test",this.javaClass.simpleName + " onDestroy")
    }

}


class MyJobIntentService : JobIntentService(){

    override fun onHandleWork(intent: Intent) {
        Log.i("Test", "Executing work: $intent")
        val label = intent.getStringExtra("label")
        Log.i("Test", "label : $label")

    }


    companion object {

        fun enqueueWork( context : Context,  work: Intent){
           enqueueWork(context,MyJobIntentService::class.java,10000, work)
        }
    }

}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService : JobService(){

    var loop = true
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.i("Test",this.javaClass.simpleName + " onRebind")

    }
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.i("Test",this.javaClass.simpleName + " onStopJob")

        loop = false
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Thread({
            while (loop){
                Thread.sleep(1000)
                Log.i("Test","run ....")
            }


        }).start()
        Log.i("Test",this.javaClass.simpleName + " onStartJob")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Test",this.javaClass.simpleName + " onDestroy")
    }

}