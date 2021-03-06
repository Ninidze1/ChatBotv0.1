package com.example.gelaBot.utils

import com.example.gelaBot.utils.Constants.OPEN_GOOGLE
import com.example.gelaBot.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {

            message.contains("aagde") && message.contains("moneta") -> {
                val r = (0..1).random()
                val result = if (r == 0) "საფასური " else "ბორჯღალო"

                "ავაგდე მონეტა და ამოვიდა $result"
            }

            message.contains("gamotvale") || message.contains("ramdenia") -> {
                val equation: String? = message.substringAfterLast("gamotvale")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "მაგდენს ვერ ვქაჩავ."
                }
            }

            message.contains("gamarjoba") -> {
                when (random) {
                    0 -> "გაუმარჯოს"
                    1 -> "ზდ ბრატ!"
                    else -> "მაგდენს ვერ ვქაჩავ!" }
            }

            message.contains("ratom") -> {
                when (random) {
                    0 -> "იმიტომ :)"
                    else -> "მაგდენს ვერ ვქაჩავ!"
                }
            }

            message.contains("rogor xar") -> {
                when (random) {
                    0 -> "კარგად ვარ, მადლობა"
                    1 -> "რავი, შაურმას შევჭამდი"
                    2 -> "კარგად, თავად?"
                    else -> "მაგდენს ვერ ვქაჩავ!"
                }
            }

            (message.contains("saati") || message.contains("dro")) -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            message.contains("search")-> {
                OPEN_SEARCH
            }

            else -> {
                when (random) {
                    0 -> "მაგაზე პასუხს არ გაგცემ!"
                    else -> "მაგდენს ვერ ვქაჩავ"
                }
            }
        }
    }
}