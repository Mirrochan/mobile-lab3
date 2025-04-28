package com.example.lab3
import kotlinx.coroutines.*

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
    fun main() = runBlocking  {
        println(" Creating students")

        val student1 = Student(" alice   ", 20, listOf(90, 85, 88))

        val student2 = Student(name = "         bob")  // secondary constructor with named argument

        println("\n Initial state ")
        println(student1)
        println(student2)

        println("\n Check status lazy ")
        println("${student1.name} is ${student1.status}")
        println("${student2.name} is ${student2.status}")

        println("\n Operator overload ")

        val combinedStudent = student1 + student2
        println("Combined student: $combinedStudent")

        val multipliedStudent = student1 * 2
        println("Multiplied grades: $multipliedStudent")

        val student3 = Student("Alice", 20, listOf(90, 85, 88))
        println("Student1 == Student3: ${student1 == student3}")

        println("\nCreating group")
        val group = Group(student1, student2, student3)
        println(group)

        println("\nTop student: ${group.getTopStudent()}")

        println("\nFetching grades asynchronously")
        val deferred = async { fetchGradesFromServer() }
        val newGrades = deferred.await()
        student2.updateGrades(newGrades)

        println("\nUpdated student2 grades")
        println(student2)

        println("\nProcess grades ( add +5 to each)")
        student2.processGrades { it + 5 }
        println(student2)

        group.analyzeGroupPerformance()
    }

