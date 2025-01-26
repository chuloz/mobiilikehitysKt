const val EPS = 0.0000001

class Complex(val real: Double, val imaginary: Double) {

    constructor(real: Int, imaginary: Int) : this(real.toDouble(), imaginary.toDouble())

    operator fun plus(other: Complex): Complex {
        return Complex(real + other.real, imaginary + other.imaginary)
    }

    operator fun minus(other: Complex): Complex {
        return Complex(real - other.real, imaginary - other.imaginary)
    }

    operator fun times(other: Complex): Complex {
        return Complex(
            real * other.real - imaginary * other.imaginary,
            real * other.imaginary + imaginary * other.real
        )
    }

    val abs: Double
        get() = kotlin.math.sqrt(real * real + imaginary * imaginary)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Complex) return false
        return kotlin.math.abs(real - other.real) < EPS &&
                kotlin.math.abs(imaginary - other.imaginary) < EPS
    }

    override fun toString(): String {
        return "$real + ${imaginary}i"
    }

    override fun hashCode(): Int {
        return real.hashCode() * 31 + imaginary.hashCode()
    }
}