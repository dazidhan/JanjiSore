package com.example.janjisore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class AddAddressActivity : AppCompatActivity() {

    private lateinit var btnSave: Button
    private lateinit var etAddress: EditText
    private lateinit var etAddressDetail: EditText
    private lateinit var tvGreeting: TextView
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        initViews()
        setupClickListeners()
        setupBottomNavigation()
    }

    private fun initViews() {
        btnSave = findViewById(R.id.btnSave)
        etAddress = findViewById(R.id.etAddress)
        etAddressDetail = findViewById(R.id.etAddressDetail)
        tvGreeting = findViewById(R.id.tvGreeting)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        tvGreeting.text = "Halo Dhaffa"
    }

    private fun setupClickListeners() {
        btnSave.setOnClickListener {
            val address = etAddress.text.toString()
            val addressDetail = etAddressDetail.text.toString()

            if (address.isBlank() || addressDetail.isBlank()) {
                showToast("Harap isi semua field alamat")
            } else {
                showToast("Alamat disimpan")
                // Simpan alamat atau navigasi ke screen lain
                val intent = Intent(this, CheckoutAddressActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.menu_cart -> {
                    showToast("Fitur cart belum tersedia")
                    true
                }
                R.id.menu_profile -> {
                    // Already in profile/address section
                    true
                }
                else -> false
            }
        }

        // Set profile sebagai selected
        bottomNavigation.selectedItemId = R.id.menu_profile
    }

    private fun showToast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}