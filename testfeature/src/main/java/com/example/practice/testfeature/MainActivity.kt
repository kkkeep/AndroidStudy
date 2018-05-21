package com.example.practice.testfeature

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import java.util.*
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
@SuppressLint("ServiceCast")
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //startService(Intent(this,BackgroundService::class.java))

        //registerReceiver(MyBroadcastReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))


        Log.i("Test", Date(SystemClock.elapsedRealtime()).toString())

       /* window.decorView.postDelayed({
            Log.i("Test","post delayed")
            val intent = Intent()
            intent.putExtra("label","hello .......")
            MyJobIntentService.enqueueWork(this,intent)
        },1000 * 70)*/






      // jobBuild = JobInfo.Builder(2, ComponentName(this,MyJobService::class.java))
        window.decorView.postDelayed({
           // jobBuild.setMinimumLatency(2* 1000)

            //jobScheduler.schedule(jobBuild.build())

        },1000* 6)




    }

    private val jobScheduler1: JobScheduler
        get() {
            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            return jobScheduler
        }


    fun onScheduler(view: View){
        val jobScheduler = jobScheduler1

        var jobBuild = JobInfo.Builder(10000000, ComponentName(this,MyJobService::class.java))

        jobBuild.setMinimumLatency(1 * 1000)
        //jobBuild.setRequiresDeviceIdle(true)


        jobScheduler.schedule(jobBuild.build())
    }

    fun cancel(view: View){

        jobScheduler1.cancel(10000000)
    }
}
