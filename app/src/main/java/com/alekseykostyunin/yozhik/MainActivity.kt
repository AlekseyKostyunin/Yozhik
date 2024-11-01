package com.alekseykostyunin.yozhik

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

val receiver = YozhikReceiver()

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.MyTextView)

        val filter = IntentFilter("Yozhik!")

        registerReceiver(receiver, filter, RECEIVER_EXPORTED)

        //Call Loshadka
        sendBroadcast(Intent("Loshadka!"));

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onResume() {
        super.onResume()
        updateCounters()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var textView: TextView
        @SuppressLint("SetTextI18n")
        fun updateCounters() {
            if (::textView.isInitialized) {
                textView.text = "Yezhik heard loshadka " + receiver.callsCounter + " times"
            }
        }
    }
}