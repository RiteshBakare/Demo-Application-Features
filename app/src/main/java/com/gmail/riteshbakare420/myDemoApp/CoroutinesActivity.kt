package com.gmail.riteshbakare420.myDemoApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gmail.riteshbakare420.myDemoApp.databinding.ActivityCoroutinesBinding
import kotlin.concurrent.thread

class CoroutinesActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("MyAppThread", Thread.currentThread().name)

        binding.btnCount.setOnClickListener {
            binding.tvCounter.text = "${binding.tvCounter.text.toString().toInt()+1}"
            Log.e("MyAppThread", Thread.currentThread().name)
        }

        binding.btnLong.setOnClickListener {
            thread(start = true) {
                longRunningTask()
                Log.e("MyAppThread", Thread.currentThread().name)
            }
        }

    }


    private fun longRunningTask() {

        for(i in 1..10000000000L) {

        }
        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
    }

}