package com.example.janjisore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class CheckoutActivity : AppCompatActivity() {

    private lateinit var etFullName: TextInputEditText
    private lateinit var etAddress: TextInputEditText
    private lateinit var etAddressNotes: TextInputEditText
    private lateinit var btnOrderAndPay: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        initViews()
        setupClickListeners()
        fillDefaultData()

        // DEBUG: Cek apakah data orders sampai
        val orders = intent.getSerializableExtra("ORDERS") as? ArrayList<Order>
        println("DEBUG CheckoutActivity: Orders received = ${orders?.size ?: 0}")
    }

    private fun initViews() {
        etFullName = findViewById(R.id.etFullName)
        etAddress = findViewById(R.id.etAddress)
        etAddressNotes = findViewById(R.id.etAddressNotes)
        btnOrderAndPay = findViewById(R.id.btnOrderAndPay)
    }

    // CheckoutActivity.kt
    private fun setupClickListeners() {
        btnOrderAndPay.setOnClickListener {
            if (validateForm()) {
                // 1. Buat intent baru untuk ke OrderConfirmationActivity
                val newIntent = Intent(this, OrderConfirmationActivity::class.java)

                // 2. Pass data alamat ke intent baru
                newIntent.putExtra("FULL_NAME", etFullName.text.toString())
                newIntent.putExtra("ADDRESS", etAddress.text.toString())
                newIntent.putExtra("ADDRESS_NOTES", etAddressNotes.text.toString())

                // 3. Ambil data "ORDERS" dari intent LAMA (intent milik activity ini)
                //    Gunakan 'this.intent' atau cukup 'intent'
                val incomingOrders = this.intent.getSerializableExtra("ORDERS") as? ArrayList<Order>

                if (incomingOrders != null) {
                    // 4. Masukkan data orders ke intent BARU
                    newIntent.putExtra("ORDERS", incomingOrders)
                    println("DEBUG: Sending ${incomingOrders.size} orders to OrderConfirmation")
                } else {
                    // Fallback jika tidak ada data orders
                    val defaultOrder = Order("Kopi Susu Gula Aren", 1, 25000)
                    newIntent.putExtra("ORDERS", ArrayList(listOf(defaultOrder)))
                    println("DEBUG: Using default order")
                }

                startActivity(newIntent)
            }
        }
    }

    private fun fillDefaultData() {
        etFullName.setText("Dhaffa Zikrullah Ramadhan")
        etAddress.setText("Jl. Merdeka No. 123, Kelurahan Sukajadi, Kecamatan Sumur Bandung")
        etAddressNotes.setText("Rumah dengan pagar biru, sebelah warung makan Padang")
    }

    private fun validateForm(): Boolean {
        return when {
            etFullName.text.isNullOrBlank() -> {
                showToast("Harap isi nama lengkap")
                false
            }
            etAddress.text.isNullOrBlank() -> {
                showToast("Harap isi alamat")
                false
            }
            etAddressNotes.text.isNullOrBlank() -> {
                showToast("Harap isi patokan alamat")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}