package com.example.first

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.first.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailTextWatcher()
        passwordTextWatcher()

        binding.nextButton.setOnClickListener() {
            val validEmail = binding.emailContainer.helperText == "Correct"
            val validPassword = binding.passwordContainer.helperText == "Correct"

            if (validEmail && validPassword) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }

        }
    }

    //Валидация почты
    private fun emailTextWatcher() {
        binding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emailHelperText()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }


    private fun validEmail(): String {
        val enteredEmail = binding.emailEditText.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches()) {
            return "Invalid email"
        }
        return "Correct"
    }

    private fun emailHelperText() {
        binding.emailContainer.helperText = validEmail()

        if (validEmail() == "Correct") {
            val colorGreen = ContextCompat.getColor(this, R.color.green)
            binding.emailContainer.setHelperTextColor(ColorStateList.valueOf(colorGreen))
        } else {
            val colorRed = ContextCompat.getColor(this, R.color.red)
            binding.emailContainer.setHelperTextColor(ColorStateList.valueOf(colorRed))
        }
    }


    //Валидация пароля
    private fun passwordTextWatcher() {
        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                passwordHelperText()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun validPassword(): String {
        val passwordText = binding.passwordEditText.text.toString().trim()
        if (passwordText.length < 6) {
            return "Invalid password. Minimum 6 characters"
        }
        return "Correct"
    }

    private fun passwordHelperText() {
        binding.passwordContainer.helperText = validPassword()

        if (validPassword() == "Correct") {
            val colorGreen = ContextCompat.getColor(this, R.color.green)
            binding.passwordContainer.setHelperTextColor(ColorStateList.valueOf(colorGreen))
        } else {
            val colorRed = ContextCompat.getColor(this, R.color.red)
            binding.passwordContainer.setHelperTextColor(ColorStateList.valueOf(colorRed))
        }
    }


}