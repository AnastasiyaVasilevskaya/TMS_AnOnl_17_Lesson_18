package com.example.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }, 3000)
        }
    }
}