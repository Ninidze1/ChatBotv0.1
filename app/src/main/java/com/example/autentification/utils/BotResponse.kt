package com.example.autentification.utils

import com.example.autentification.utils.Constants.OPEN_GOOGLE
import com.example.autentification.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {

            //Flips a coin
            message.contains("ააგდე") && message.contains("მონეტა") -> {
                val r = (0..1).random()
                val result = if (r == 0) "საფასური" else "ბორჯღალო"

                "ავაგდე მონეტა და ამოვიდა $result"
            }

            //Math calculations
            message.contains("გამოთვალე") -> {
                val equation: String? = message.substringAfterLast("გამოთვალე")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "ბოდიშით, მაგდენს ვერ ვქაჩავ."
                }
            }

            //Hello
            message.contains("გამარჯობა") -> {
                when (random) {
                    0 -> "გაუმარჯოს!"
                    1 -> "ზდ"
                    2 -> "ბარო!"
                    else -> "შეცდომა!" }
            }

            //How are you?
            message.contains("როგორ ხარ") -> {
                when (random) {
                    0 -> "იმიტომ :)"
                    else -> "შეცდომა!"
                }
            }

            message.contains("რატომ") -> {
                when (random) {
                    0 -> "კარგად ვარ, მადლობა"
                    1 -> "რავი, შაურმას შევჭამდი"
                    2 -> "კარგად, თავად?"
                    else -> "შეცდომა!"
                }
            }

            //What time is it?
            (message.contains("დრო") || message.contains("საათი")) && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("გახსენი") && message.contains("გუგლი")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("მოძებნე")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "ვერ გავიგე რისი თქმა გინდათ"
                    1 -> "მაგაზე პასუხს არ გაგცემ"
                    2 -> "რავიცი აბა"
                    else -> "შეცდომა"
                }
            }
        }
    }
}