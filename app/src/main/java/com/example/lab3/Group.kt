package com.example.lab3

class Group(vararg students: Student) {
    private val studentList = students.toList()

    operator fun get(index: Int): Student = studentList[index]

    fun getTopStudent(): Student? {
        return studentList.maxByOrNull { it.getAverage() }
    }
    override fun toString(): String {
        return "Group(students=${studentList.joinToString()})"
    }
    fun analyzeGroupPerformance() {
        if (studentList.isEmpty()) {
            println(" Group is empty.")
            return
        }

        println(" Group Performance Report:")

        var totalAverage = 0.0
        var minAverage = Double.MAX_VALUE
        var maxAverage = Double.MIN_VALUE

        for (student in studentList) {
            val avg = student.getAverage()
            totalAverage += avg
            println("${student.name}: average = %.2f".format(avg))
            if (avg < minAverage) minAverage = avg
            if (avg > maxAverage) maxAverage = avg
        }

        val groupAverage = totalAverage / studentList.size
        println("\nGroup average: %.2f".format(groupAverage))
        println("Highest student average: %.2f".format(maxAverage))
        println("Lowest student average: %.2f".format(minAverage))

        val groupLevel = when {
            groupAverage >= 90 -> " Excellent"
            groupAverage >= 75 -> "Good"
            groupAverage >= 60 -> " Average"
            else -> " Needs Improvement"
        }

        println("Overall group level: $groupLevel")
    }
}
