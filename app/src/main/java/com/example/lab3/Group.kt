package com.example.lab3

class Group(vararg students: Student) {
    private val studentList = students.toList()

    operator fun get(index: Int): Student = studentList[index]

    fun getTopStudent(): Student? {
        return studentList.maxByOrNull { it.getAverage() }
    }

    override fun toString(): String {
        return studentList.joinToString("\n")
    }
}
