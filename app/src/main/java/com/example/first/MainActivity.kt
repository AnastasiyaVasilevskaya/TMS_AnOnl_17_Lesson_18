package com.example.first

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.example.first.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateButtonState()
        emailTextWatcher()
        passwordTextWatcher()

        binding.nextButton.setOnClickListener() {
            val intent = Intent(this, SecondActivity::class.java)

            // Добавляем данные о почте и пароле
            intent.putExtra("email", binding.emailEditText.text.toString())
            intent.putExtra("password", binding.passwordEditText.text.toString())

            // Добавляем данные о состоянии чекбоксов
            if (binding.checkBox1.isChecked) {
                val selectedCheckBoxText = binding.checkBox1.text.toString()
                intent.putExtra("checkBox1Checked", selectedCheckBoxText)
            }

            if (binding.checkBox2.isChecked) {
                val selectedCheckBoxText = binding.checkBox2.text.toString()
                intent.putExtra("checkBox2Checked", selectedCheckBoxText)
            }

            //РадиоБаттон
            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            selectedRadioButtonId.let { id ->
                val selectedRadioButtonText = findViewById<RadioButton>(id)?.text?.toString() ?: "No RadioButton selected"
                intent.putExtra("radioButtonSelected", selectedRadioButtonText)
            }

            startActivity(intent)
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

    private fun validEmail(): Boolean {
        val enteredEmail = binding.emailEditText.text.toString().trim()
        return Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches()
    }

    private fun emailHelperText() {
        if (validEmail()) {
            binding.emailContainer.helperText = getString(R.string.correct)
            val colorGreen = ContextCompat.getColor(this, R.color.green)
            binding.emailContainer.setHelperTextColor(ColorStateList.valueOf(colorGreen))
        } else {
            binding.emailContainer.helperText = getString(R.string.invalid_email)
            val colorRed = ContextCompat.getColor(this, R.color.red)
            binding.emailContainer.setHelperTextColor(ColorStateList.valueOf(colorRed))
        }
        updateButtonState()
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

    private fun validPassword(): Boolean {
        return binding.passwordEditText.text.toString().trim().length > 5

    }

    private fun passwordHelperText() {
        if (validPassword()) {
            binding.passwordContainer.helperText = getString(R.string.correct)
            val colorGreen = ContextCompat.getColor(this, R.color.green)
            binding.passwordContainer.setHelperTextColor(ColorStateList.valueOf(colorGreen))
        } else {
            binding.passwordContainer.helperText = getString(R.string.invalid_password_mess)
            val colorRed = ContextCompat.getColor(this, R.color.red)
            binding.passwordContainer.setHelperTextColor(ColorStateList.valueOf(colorRed))
        }
        updateButtonState()
    }

    //Активация кнопки
    private fun updateButtonState() {
        binding.nextButton.isEnabled = validEmail() && validPassword()
    }
}