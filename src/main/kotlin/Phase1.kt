const val EPS = 0.0001

// Human class
open class Human(val name: String, var age: Int) {
    fun getOlder() {
        age++
    }
}

// CourseRecord class
data class CourseRecord(
    val name: String,
    val yearCompleted: Int,
    val credits: Int,
    val grade: Double
)

// Student class
class Student(name: String, age: Int) : Human(name, age) {
    val courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        courses.add(course)
    }

    fun weightedAverage(): Double {
        val totalCredits = courses.sumOf { it.credits }
        val weightedSum = courses.sumOf { it.grade * it.credits }
        return if (totalCredits > 0) weightedSum / totalCredits else 0.0
    }

    fun weightedAverage(year: Int): Double {
        val filteredCourses = courses.filter { it.yearCompleted == year }
        val totalCredits = filteredCourses.sumOf { it.credits }
        val weightedSum = filteredCourses.sumOf { it.grade * it.credits }
        return if (totalCredits > 0) weightedSum / totalCredits else 0.0
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val grades = courses.map { it.grade }
        return if (grades.isNotEmpty()) Pair(grades.min(), grades.max()) else Pair(0.0, 0.0)
    }
}

// Major class
class Major(val name: String) {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage() }
        return Triple(averages.min(), averages.max(), averages.average())
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {
        val courseGrades = students.flatMap { student ->
            student.courses.filter { it.name == courseName }.map { it.grade }
        }
        return if (courseGrades.isNotEmpty()) {
            Triple(courseGrades.min(), courseGrades.max(), courseGrades.average())
        } else {
            Triple(0.0, 0.0, 0.0)
        }
    }
}