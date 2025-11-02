package com.example.janjisore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var rvDrinks: RecyclerView
    private lateinit var drinkAdapter: DrinkAdapter
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var etSearch: EditText
    private lateinit var tvGreeting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        setupRecyclerView()
        setupBottomNavigation()
        setupSearch()
    }

    private fun initViews() {
        rvDrinks = findViewById(R.id.rvDrinks)
        bottomNavigation = findViewById(R.id.bottomNavigation)
        etSearch = findViewById(R.id.etSearch)
        tvGreeting = findViewById(R.id.tvGreeting)

        // Set greeting dengan nama user
        tvGreeting.text = "Halo Dhaffa"
    }

    private fun setupRecyclerView() {
        val drinkList = DummyData.getDrinkMenu()
        drinkAdapter = DrinkAdapter(drinkList) { drink ->
            // Navigate langsung ke CheckoutAddressActivity ketika minuman dipilih
            val intent = Intent(this, CheckoutAddressActivity::class.java)
            intent.putExtra("SELECTED_DRINK", drink)
            startActivity(intent)
        }
        rvDrinks.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = drinkAdapter
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    // Already in home
                    true
                }
                R.id.menu_cart -> {
                    // Navigate to cart
                    showToast("Fitur cart belum tersedia")
                    true
                }
                R.id.menu_profile -> {
                    val intent = Intent(this, AddAddressActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        // Set home sebagai selected
        bottomNavigation.selectedItemId = R.id.menu_home
    }

    private fun setupSearch() {
        etSearch.setOnClickListener {
            showToast("Fitur pencarian belum tersedia")
        }
    }

    private fun showToast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}