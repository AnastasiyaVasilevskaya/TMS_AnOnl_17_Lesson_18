package com.example.first

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.first.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val checkBox1Checked = intent.getStringExtra("checkBox1Checked")
        val checkBox2Checked = intent.getStringExtra("checkBox2Checked")
        val radioButtonSelected : String? = intent.getStringExtra("radioButtonSelected")

        binding.result.text =
            "Email: $email\nPassword: $password\nCheckBox1: $checkBox1Checked\nCheckBox2: $checkBox2Checked\nRadioButton: $radioButtonSelected"

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}