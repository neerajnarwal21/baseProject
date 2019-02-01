package com.example.sample.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import com.example.sample.R
import com.example.sample.utils.Const
import com.example.sample.utils.TokenRefresh


/**
 * Created by Neeraj Narwal on 5/5/17.
 */


class SplashActivity : BaseActivity() {
    private val handler = Handler()

    private val waitingRunnable = Runnable {
        if (!isFinishing && store.getString(Const.DEVICE_TOKEN) == null) {
            showDialogNoServices()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        val mNotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        mNotificationManager.cancelAll()
//        createNotificationChannel()
//        log("Token >>>> Start app")
//        if (initFCM())
//            Handler().postDelayed({
//                log("Token >>>> After 2 sec checking token")
//                if (!isFinishing && store.getString(Const.DEVICE_TOKEN) != null)
//                    gotoMainActivity()
//                else {
//                    registerForTokenCallback()
//                    waitFor5SecMore()
//                }
//            }, 2000)
    }

    private fun showDialogNoServices() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert !")
        builder.setMessage("Unable to initialize Google Services." +
                "\nMake sure your Internet connection is good." +
                "\nYou can try restart your phone, check if google apps like maps working or not." +
                "\nIf still problem persists then contact app admin")
        builder.setPositiveButton("Close") { dialogInterface, i -> dialogInterface.dismiss() }
        builder.setOnDismissListener { exitFromApp() }
        builder.show()
    }

    private fun waitFor5SecMore() {
        log("Token >>>> Waiting for token to come")
        handler.postDelayed(waitingRunnable, 10000)
    }

    private fun registerForTokenCallback() {
        TokenRefresh.setTokenListener {
            log("Token >>>> Token is here")
            handler.removeCallbacks(waitingRunnable)
            gotoMainActivity()
        }
    }

    private fun gotoMainActivity() {
//        val intent = Intent(this, LoginSignupActivity::class.java)
//        if (getIntent().getBooleanExtra("isPush", false)) {
//            intent.putExtra("isPush", getIntent().getBooleanExtra("isPush", false))
//            intent.putExtra("orderId", getIntent().getStringExtra("orderId"))
//        }
//        startActivity(intent)
//        finish()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channelId = Const.NOTI_CHANNEL_ID
            val channelName = "Some Channel"
            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}