package com.example.lab3

class Student {
    private var _name: String
    private var _age: Int = 0
    private var _grades: List<Int> = listOf()

    var name: String
        get() = _name
        set(value) {
            _name = value.trim().replaceFirstChar { it.uppercase() }
        }
    var age: Int
        get() = _age
        set(value) {
            if (value >= 0) _age = value
        }

    val isAdult: Boolean
        get() = age >= 18

    val status: String by lazy {
        if (isAdult) "Adult" else "Minor"
    }

    // Первинний конструктор
    constructor(name: String, age: Int, grades: List<Int>) {
        this._name = name.trim().replaceFirstChar { it.uppercase() }
        this._age = if (age >= 0) age else 0
        this._grades = grades
        println("Student object created with full constructor: $_name")
    }

    // Вторинний конструктор
    constructor(name: String) {
        this._name =  name.trim().replaceFirstChar { it.uppercase() }
        this._age =0
        this._grades = emptyList()
        println("Student object created with secondary constructor: $_name")
    }

    fun getAverage(): Double {
        return if (_grades.isNotEmpty()) _grades.average() else 0.0
    }

    fun processGrades(operation: (Int) -> Int) {
        _grades = _grades.map { operation(it) }
    }

    fun updateGrades(grades: List<Int>) {
        _grades = grades
    }

    fun getGrades(): List<Int> = _grades

    override fun toString(): String {
        return "Student(name=$_name, age=$_age, grades=$_grades, avg=${"%.2f".format(getAverage())}, status=$status)"
    }

    operator fun plus(other: Student): Student {
        return Student(name = "$name & ${other.name}", age = maxOf(age, other.age), grades = _grades + other._grades)
    }

    operator fun times(multiplier: Int): Student {
        return Student(name = this.name, age = this.age, grades = _grades.map { it * multiplier })
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Student) return false
        return this.name == other.name && this.getAverage() == other.getAverage()
    }
    override fun hashCode(): Int {
        return name.hashCode() + getAverage().hashCode().toInt()
    }
}
