package com.example.janjisore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderActivity : AppCompatActivity() {

    private lateinit var rvOrders: RecyclerView
    private lateinit var btnKirim: Button
    private lateinit var tvGreeting: TextView
    private lateinit var orderAdapter: OrderAdapter
    private var selectedDrink: Drink? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initViews()
        setupRecyclerView()
        setupClickListeners()

        // Get data dari intent
        selectedDrink = intent.getSerializableExtra("SELECTED_DRINK") as? Drink
    }

    private fun initViews() {
        rvOrders = findViewById(R.id.rvOrders)
        btnKirim = findViewById(R.id.btnKirim)
        tvGreeting = findViewById(R.id.tvGreeting)

        tvGreeting.text = "Halo Dhaffa,"
    }

    private fun setupRecyclerView() {
        val orders = if (selectedDrink != null) {
            listOf(Order(selectedDrink!!.name, 1, selectedDrink!!.price))
        } else {
            // Fallback jika tidak ada data
            listOf(Order("Kopi Susu Gula Aren", 1, 25000))
        }

        orderAdapter = OrderAdapter(orders)
        rvOrders.apply {
            layoutManager = LinearLayoutManager(this@OrderActivity)
            adapter = orderAdapter
        }
    }

    private fun setupClickListeners() {
        btnKirim.setOnClickListener {
            val orders = orderAdapter.getOrders()
            if (orders.isNotEmpty()) {
                val intent = Intent(this, CheckoutActivity::class.java)
                // Convert list to ArrayList karena Serializable
                intent.putExtra("ORDERS", ArrayList(orders))
                startActivity(intent)
            } else {
                // Fallback jika tidak ada orders
                val intent = Intent(this, CheckoutActivity::class.java)
                val defaultOrder = Order("Kopi Susu Gula Aren", 1, 25000)
                intent.putExtra("ORDERS", ArrayList(listOf(defaultOrder)))
                startActivity(intent)
            }
        }
    }
}