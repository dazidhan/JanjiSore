package com.example.janjisore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DrinkAdapter(
    private val drinkList: List<Drink>,
    private val onItemClick: (Drink) -> Unit
) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDrinkName: TextView = itemView.findViewById(R.id.tvDrinkName)
        val tvDrinkDescription: TextView = itemView.findViewById(R.id.tvDrinkDescription)
        val tvDrinkPrice: TextView = itemView.findViewById(R.id.tvDrinkPrice)
        val tvTemperature: TextView = itemView.findViewById(R.id.tvTemperature) // Tambahkan ini
        val btnAdd: Button = itemView.findViewById(R.id.btnAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_drink, parent, false)
        return DrinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = drinkList[position]

        holder.tvDrinkName.text = drink.name
        holder.tvDrinkDescription.text = drink.description
        holder.tvDrinkPrice.text = "Rp ${drink.price}"
        holder.tvTemperature.text = drink.temperature

        // Set warna background berdasarkan temperature
        val temperatureColor = if (drink.temperature == "Hot") {
            ContextCompat.getColor(holder.itemView.context, R.color.warm_red)
        } else {
            ContextCompat.getColor(holder.itemView.context, R.color.cool_blue)
        }
        holder.tvTemperature.setBackgroundColor(temperatureColor)

        // Set click listener untuk tombol tambah
        holder.btnAdd.setOnClickListener {
            onItemClick(drink)
        }

        // Optional: juga bisa klik pada item itu sendiri
        holder.itemView.setOnClickListener {
            onItemClick(drink)
        }
    }

    override fun getItemCount(): Int = drinkList.size
}