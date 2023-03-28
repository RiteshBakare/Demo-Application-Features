package com.gmail.riteshbakare420.myDemoApp


import android.Manifest
import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView


class MainActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var mail: EditText
    private lateinit var btnAnimation: LottieAnimationView

    private lateinit var player: MediaPlayer

    // Constance for Notification

    val CHANNEL_ID = "CHANNEL_ID"
    val CHANNEL_NAME = "CHANNEL_NAME"
    val NOTIFICATION_ID = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        name = findViewById(R.id.et_name)
        mail = findViewById(R.id.et_mail)

        btnAnimation = findViewById(R.id.Btn_animationView)

        btnAnimation.setOnClickListener {
            btnAnimation.playAnimation()
        }

        btnAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                val intent = Intent(this@MainActivity, ActivitySecond::class.java)
                intent.putExtra(ActivitySecond.mName, name.text.toString())
                intent.putExtra(ActivitySecond.mMail, mail.text.toString())
                startActivity(intent)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })


        findViewById<Button>(R.id.ActivityRecyclerView).setOnClickListener {
            startActivity(Intent(this, ActivityRecyclerView::class.java))
        }

        findViewById<Button>(R.id.playSound).setOnClickListener {
            playSound()
        }

        findViewById<Button>(R.id.btnImages).setOnClickListener {
            startActivity(Intent(this, ImagesWithCamera::class.java))
        }

        // Notification
        createNotificationChannel() // this method will create an Notification channel

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("this is My Notification !!! ")
            .setContentText("this is the text in the Notification :-) ")
            .setSmallIcon(R.drawable.sandwithc)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)

        findViewById<Button>(R.id.btnNotification).setOnClickListener {

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"Please Give permission to Notification",Toast.LENGTH_LONG).show()
            }
            else {
                notificationManager.notify(NOTIFICATION_ID,notification)
            }
        }

    }

    private fun playSound() {
        try {
            val soundURI = Uri.parse(
                "android.resource://com.gmail.riteshbakare420.myDemoApp/" + R.raw.press_start
            )
            player = MediaPlayer.create(applicationContext, soundURI)
            player.isLooping = false
            player.start()
        } catch (e: Exception) {
            Log.e("Player", "Sound not played ")
        }
    }

    // function for notification
    fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.RED
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)
        }

    }

}