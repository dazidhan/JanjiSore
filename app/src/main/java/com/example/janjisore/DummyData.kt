package com.example.janjisore

object DummyData {
    fun getDrinkMenu(): List<Drink> {
        return listOf(
            Drink("Kopi Susu Gula Aren", "Kopi", "Menu andalan yang paling dicari.", 25000, "Hot"),
            Drink("Caffe Latte", "Kopi", "Standar kopi susu klasik.", 22000, "Hot"),
            Drink("Iced Americano", "Kopi", "Untuk pecinta kopi hitam.", 18000, "Cold"),
            Drink("Matcha Latte", "Non-Kopi", "Sangat populer dan visualnya bagus.", 28000, "Hot"),
            Drink("Iced Chocolate", "Non-Kopi", "Menu wajib untuk non-kopi.", 20000, "Cold"),
            Drink("Iced Taro Latte", "Non-Kopi", "Pilihan populer rasa ubi ungu.", 27000, "Cold")
        )
    }
}