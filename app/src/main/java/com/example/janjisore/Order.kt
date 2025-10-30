package com.example.janjisore

import java.io.Serializable

data class Order(
    val drinkName: String,
    val quantity: Int,
    val price: Int,
    var notes: String = ""
) : Serializable {
    fun getTotalPrice(): Int = price * quantity
}