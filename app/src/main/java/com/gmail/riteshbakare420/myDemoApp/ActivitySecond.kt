package com.gmail.riteshbakare420.myDemoApp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gmail.riteshbakare420.myDemoApp.databinding.ActivitySecondBinding
import java.util.*


class ActivitySecond : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivitySecondBinding

    companion object {
        const val mMail = "USER_MAIL"
        const val mName = "USER_NAME"
    }

    private lateinit  var tts : TextToSpeech

    private  var countDownTimer: CountDownTimer? = null

    private var timeDuration : Long = 60000
    private var pauseOffset : Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // this is for Dialog
        binding.btnAlertDialog.setOnClickListener {
            myAlertDialog("my Alert","This is my custom Alert Dialog for android")
        }
        binding.btnCustomDialog.setOnClickListener {
            myCustomDialog("my Custom Dialog","this is a custom dialog message for android kotlin ")
        }
        binding.btnProgressBar.setOnClickListener {
            showMyProgressBar()
        }


        // this is for intent
        binding.tvName.text =  intent.getStringExtra(mName)
        binding.tvMail.text = intent.getStringExtra(mMail)
        // intent sharing
        binding.btnShare.setOnClickListener {
            myShareFunction()
        }

        // Text to Speech
        tts = TextToSpeech(this,this)
        binding.btnTextToSpeech.setOnClickListener {
            if(binding.etTextToSpeech.text!!.isEmpty() ) {
                Toast.makeText(this,"Please Enter Something ",Toast.LENGTH_LONG).show()
            }
            else {
                speakOut(binding.etTextToSpeech.text.toString())
            }
        }


        // Custom TopBar
        setSupportActionBar(binding.myToolBar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.myToolBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


        // Count Down Timer

        binding.tvTimer.text = (timeDuration/1000).toString()
        binding.tvStart.setOnClickListener {
            startTimer(pauseOffset)
        }
        binding.tvStop.setOnClickListener {
            pauseTimer()
        }
        binding.tvReset.setOnClickListener {
            resetTimer()
        }

    }


    // CountDown Timer
    private fun startTimer(pauseOffsetL : Long) {
        countDownTimer = object : CountDownTimer(timeDuration-pauseOffsetL,1000) {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timeDuration - millisUntilFinished
                binding.tvTimer.text = (millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                Toast.makeText(this@ActivitySecond,"Timer Finished ",Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    private fun pauseTimer() {
        if(countDownTimer != null) {
            countDownTimer!!.cancel()
        }
    }

    private fun resetTimer() {
        if(countDownTimer != null) {
            countDownTimer!!.cancel()
            binding.tvTimer.text = (timeDuration/1000).toString()
            countDownTimer=null
            pauseOffset = 0
        }
    }


    // TopBar menu handling
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_actionbar_menu, menu)

        val about = menu?.findItem(R.id.aboutUs)
        val share = menu?.findItem(R.id.star)

        about?.setOnMenuItemClickListener {
            Toast.makeText(this,"about US ",Toast.LENGTH_LONG).show()
            true
        }
        share?.setOnMenuItemClickListener {
            myShareFunction()
            Toast.makeText(this,"Share Pressed ",Toast.LENGTH_LONG).show()
            true
        }
        return super.onCreateOptionsMenu(menu)
    }



    // Intent sharing
    private fun myShareFunction() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"www.google.com")

        val chooser = Intent.createChooser(intent,"share me")
        startActivity(chooser)
    }


    // Text To Speech Demo
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","Language not supported")
            }
        }
        else {
            Log.e("TTS","Initialization Failed ")
        }
    }
    private fun speakOut(text: String) {
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    // Progress Bar Function
    private fun showMyProgressBar() {
        val progressBar = Dialog(this)
        progressBar.setContentView(R.layout.my_custom_progressbar)
        //progressBar.setCancelable(false)
        progressBar.show()
    }

    // Alert Dialog
    private fun myAlertDialog(title: String,message : String) {
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(R.drawable.baseline_emergency_24)
        builder.setPositiveButton("Yes"){dialog,which->
            Toast.makeText(this,"Yes Pressed ",Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){dialog,which->
            Toast.makeText(this,"No Pressed ",Toast.LENGTH_LONG).show()
        }
        builder.setNeutralButton("cancel"){dialog,which->
            Toast.makeText(this,"Cancel Pressed ",Toast.LENGTH_LONG).show()
        }
        builder.create().show()
    }

    // Custom Dialog
    private fun myCustomDialog(title: String, message:String) {
        val builder = Dialog(this)
        builder.setContentView(R.layout.my_custom_dialog)

        val myTitle = builder.findViewById<TextView>(R.id.title)
        myTitle.text = title

        val myMessage = builder.findViewById<TextView>(R.id.message)
        myMessage.text=message

        val cancel = builder.findViewById<TextView>(R.id.tv_cancel)
        cancel.setOnClickListener {
            Toast.makeText(this,"Cancel Pressed ",Toast.LENGTH_LONG).show()
        }
        val ok = builder.findViewById<TextView>(R.id.tv_ok)
        ok.setOnClickListener {
            Toast.makeText(this,"ok Pressed ",Toast.LENGTH_LONG).show()
        }
        builder.create()
        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }

}