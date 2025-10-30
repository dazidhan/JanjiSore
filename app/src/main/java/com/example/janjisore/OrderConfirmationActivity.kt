package com.example.janjisore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class OrderConfirmationActivity : AppCompatActivity() {

    private lateinit var btnPayNow: Button
    private lateinit var tvCustomerName: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvAddressDetail: TextView
    private lateinit var tvDrink1Name: TextView
    private lateinit var tvDrink1Qty: TextView
    private lateinit var tvDrink2Name: TextView
    private lateinit var tvDrink2Qty: TextView
    private lateinit var tvTotalPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        // DEBUG: Cek data yang diterima
        val orders = intent.getSerializableExtra("ORDERS") as? ArrayList<Order>
        println("DEBUG OrderConfirmation: Orders received = ${orders?.size ?: 0}")

        initViews()
        setupData()
        setupClickListeners()
    }

    private fun initViews() {
        btnPayNow = findViewById(R.id.btnPayNow)
        tvCustomerName = findViewById(R.id.tvCustomerName)
        tvAddress = findViewById(R.id.tvAddress)
        tvAddressDetail = findViewById(R.id.tvAddressDetail)
        tvDrink1Name = findViewById(R.id.tvDrink1Name)
        tvDrink1Qty = findViewById(R.id.tvDrink1Qty)
        tvDrink2Name = findViewById(R.id.tvDrink2Name)
        tvDrink2Qty = findViewById(R.id.tvDrink2Qty)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
    }

    private fun setupData() {
        // Get data dari intent
        val fullName = intent.getStringExtra("FULL_NAME") ?: "Dhaffa Zikrullah Ramadhan"
        val address = intent.getStringExtra("ADDRESS") ?: "Jl. Merdeka No. 123, Kelurahan Sukajadi, Kecamatan Sumur Bandung"
        val addressNotes = intent.getStringExtra("ADDRESS_NOTES") ?: "Rumah dengan pagar biru, sebelah warung makan Padang"
        val orders = intent.getSerializableExtra("ORDERS") as? ArrayList<Order>

        println("DEBUG OrderConfirmation: Setting up data - Orders: ${orders?.size}")

        // Set data alamat
        tvCustomerName.text = fullName
        tvAddress.text = address
        tvAddressDetail.text = addressNotes

        // Set data pesanan
        if (orders != null && orders.isNotEmpty()) {
            // Minuman pertama
            val firstOrder = orders[0]
            tvDrink1Name.text = firstOrder.drinkName
            tvDrink1Qty.text = "${firstOrder.quantity}x"

            // Minuman kedua (jika ada)
            if (orders.size > 1) {
                val secondOrder = orders[1]
                tvDrink2Name.text = secondOrder.drinkName
                tvDrink2Qty.text = "${secondOrder.quantity}x"
                tvDrink2Name.visibility = TextView.VISIBLE
                tvDrink2Qty.visibility = TextView.VISIBLE
            }

            // Hitung total harga
            val totalPrice = orders.sumOf { it.getTotalPrice() }
            tvTotalPrice.text = "Rp $totalPrice"

            println("DEBUG OrderConfirmation: Data setup completed successfully")
        } else {
            // Data default jika tidak ada orders
            tvDrink1Name.text = "Kopi Susu Gula Aren"
            tvDrink1Qty.text = "1x"
            tvTotalPrice.text = "Rp 25.000"
            println("DEBUG OrderConfirmation: Using default data")
        }
    }

    private fun setupClickListeners() {
        btnPayNow.setOnClickListener {
            Toast.makeText(this, "Pesanan diproses!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}