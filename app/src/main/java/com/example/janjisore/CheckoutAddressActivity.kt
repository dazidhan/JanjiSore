package com.example.janjisore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CheckoutAddressActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etAddressDetail: EditText
    private lateinit var btnContinue: Button

    private var selectedDrink: Drink? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_address)

        initViews()
        setupClickListeners()

        // Get data minuman yang dipilih dari Home
        selectedDrink = intent.getSerializableExtra("SELECTED_DRINK") as? Drink

        println("DEBUG CheckoutAddress: Received drink - ${selectedDrink?.name}")
    }

    private fun initViews() {
        etName = findViewById(R.id.etName)
        etAddress = findViewById(R.id.etAddress)
        etAddressDetail = findViewById(R.id.etAddressDetail)
        btnContinue = findViewById(R.id.btnContinue)

        // Set data default
        etName.setText("Dhaffa Zikrullah Ramadhan")
        etAddress.setText("Jl. Merdeka No. 123, Kelurahan Sukajadi, Kecamatan Sumur Bandung")
        etAddressDetail.setText("Rumah dengan pagar biru, sebelah warung makan Padang")
    }

    private fun setupClickListeners() {
        btnContinue.setOnClickListener {
            if (validateForm()) {
                val intent = Intent(this, OrderConfirmationActivity::class.java)

                // Pass data alamat
                intent.putExtra("FULL_NAME", etName.text.toString())
                intent.putExtra("ADDRESS", etAddress.text.toString())
                intent.putExtra("ADDRESS_NOTES", etAddressDetail.text.toString())

                // Pass data minuman yang dipilih
                if (selectedDrink != null) {
                    // Buat order dari minuman yang dipilih
                    val order = Order(
                        drinkName = selectedDrink!!.name,
                        quantity = 1, // Default quantity 1
                        price = selectedDrink!!.price
                    )

                    // Kirim sebagai list orders
                    intent.putExtra("ORDERS", ArrayList(listOf(order)))
                    println("DEBUG CheckoutAddress: Sending order for ${selectedDrink!!.name}")
                } else {
                    // Fallback jika tidak ada minuman yang dipilih
                    val defaultOrder = Order("Kopi Susu Gula Aren", 1, 25000)
                    intent.putExtra("ORDERS", ArrayList(listOf(defaultOrder)))
                    println("DEBUG CheckoutAddress: Using default order")
                }

                startActivity(intent)
            }
        }
    }

    private fun validateForm(): Boolean {
        return when {
            etName.text.isNullOrBlank() -> {
                showToast("Harap isi nama lengkap")
                false
            }
            etAddress.text.isNullOrBlank() -> {
                showToast("Harap isi alamat")
                false
            }
            etAddressDetail.text.isNullOrBlank() -> {
                showToast("Harap isi detail alamat")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}