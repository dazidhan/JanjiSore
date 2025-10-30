package com.example.janjisore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class WelcomeActivity : AppCompatActivity() {

    private lateinit var btnStartNow: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        btnStartNow = findViewById(R.id.btnStartNow)
    }

    private fun setupClickListeners() {
        btnStartNow.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)  // PASTIKAN TIDAK ADA HURUF 'a' DI DEPAN
        }
    }
}