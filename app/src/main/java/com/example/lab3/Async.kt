package com.example.lab3

import kotlinx.coroutines.delay

suspend fun fetchGradesFromServer(): List<Int> {
    delay(2000)
    println("Grades fetched from server.")
    return listOf(88, 75, 92, 80, 95)
}
