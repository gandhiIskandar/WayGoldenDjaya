package com.binamultimediaindonesia.waygoldendjaya.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.binamultimediaindonesia.waygoldendjaya.MainActivity
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.PUSHER_APP_KEY
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.PUSHER_CLUSTER
import com.binamultimediaindonesia.waygoldendjaya.streaming_activity.StreamingActivity
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.PusherEvent
import com.pusher.client.channel.SubscriptionEventListener
import java.lang.Exception


class NotificationService : Service() {

    private lateinit var pusher:Pusher

    companion object {
        var IS_ACTIVE = false
        var GROUP_NAME = ""
    }

    override fun onCreate() {
        super.onCreate()
        val options = PusherOptions()
        options.setCluster(PUSHER_CLUSTER)
        pusher = Pusher(PUSHER_APP_KEY, options)
    }



    private fun showNotification(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel("wgd", "Way Golden Djaya", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
// Membuat suara notifikasi menggunakan default notification sound
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)


        val notificationBuilder = NotificationCompat.Builder(this, "wgd")
            .setContentTitle("Way Golden Djaya")
            .setContentText("Muthawiff telah memasuki ruangan streaming")
            .setSmallIcon(R.drawable.ic__livegroup)
            .setSound(defaultSoundUri)
            .setContentIntent(createPendingIntent())
            .setAutoCancel(true)

        val notification = notificationBuilder.build()
        notificationManager.notify(1, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        pusher.disconnect()
        pusher.unsubscribe("channel1")
    }




    private fun createPendingIntent(): PendingIntent {
        // Implementasikan logika untuk membuat PendingIntent
        // Ini mungkin membuka aktivitas terkait dengan notifikasi Anda
        val intent = Intent(this,StreamingActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        return PendingIntent.getActivity(this, 0, intent,  PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT)
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {



        // Subscribe ke saluran (channel) yang sesuai dengan event yang ingin Anda tangkap
        val channel = pusher.subscribe(GROUP_NAME)

        // Menangani event dari saluran
        channel.bind("streaming", object : SubscriptionEventListener {
            override fun onEvent(event: PusherEvent?) {
                if (event != null) {

                    showNotification()
                }
            }

            override fun onError(message: String?, e: Exception?) {
                super.onError(message, e)
            }
        })

        // Terhubung ke Pusher
        pusher.connect()

        Log.d("serviceLog", "service sudah aman")

        return START_STICKY
    }



    override fun onBind(p0: Intent?): IBinder? {
       return null
    }
}