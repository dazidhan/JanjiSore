package com.example.janjisore

object DummyData {
    fun getDrinkMenu(): List<Drink> {
        return listOf(
            Drink(
                "Kopi Susu Gula Aren",
                "Kopi",
                "Menu andalan yang paling dicari.",
                25000,
                "Hot",
                R.drawable.kopi_susu_gula_aren
            ),
            Drink(
                "Caffe Latte",
                "Kopi",
                "Standar kopi susu klasik.",
                22000,
                "Hot",
                R.drawable.coffe_latte
            ),
            Drink(
                "Iced Americano",
                "Kopi",
                "Untuk pecinta kopi hitam.",
                18000,
                "Cold",
                R.drawable.iced_americano
            ),
            Drink(
                "Matcha Latte",
                "Non-Kopi",
                "Sangat populer dan visualnya bagus.",
                28000,
                "Hot",
                R.drawable.matcha_latte
            ),
            Drink(
                "Iced Chocolate",
                "Non-Kopi",
                "Menu wajib untuk non-kopi.",
                20000,
                "Cold",
                R.drawable.iced_choclate
            ),
            Drink(
                "Iced Taro Milk",
                "Non-Kopi",
                "Pilihan populer rasa ubi ungu.",
                27000,
                "Cold",
                R.drawable.iced_taro_milk
            )
        )
    }
}