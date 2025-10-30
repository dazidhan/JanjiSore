package com.example.janjisore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private var orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDrinkName: TextView = itemView.findViewById(R.id.tvDrinkName)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val etNotes: EditText = itemView.findViewById(R.id.etNotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]

        holder.tvDrinkName.text = order.drinkName
        holder.tvQuantity.text = "Qty: ${order.quantity}"
        holder.tvPrice.text = "Rp ${order.getTotalPrice()}"
        holder.etNotes.setText(order.notes)

        holder.etNotes.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                order.notes = holder.etNotes.text.toString()
            }
        }
    }

    override fun getItemCount(): Int = orders.size

    fun getOrders(): List<Order> {
        return orders
    }
}