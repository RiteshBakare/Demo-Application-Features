package com.gmail.riteshbakare420.myDemoApp


import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView


class MainActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var mail: EditText
    private lateinit var btnAnimation: LottieAnimationView

    private lateinit var player: MediaPlayer

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
            startActivity(Intent(this,ImagesWithCamera::class.java))
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

}