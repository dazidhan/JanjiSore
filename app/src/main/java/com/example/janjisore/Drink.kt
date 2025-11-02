package com.example.janjisore

import java.io.Serializable

data class Drink(
    val name: String,
    val category: String,
    val description: String,
    val price: Int,
    val temperature: String,
    val imageResId: Int // Tambahkan field ini
) : Serializable