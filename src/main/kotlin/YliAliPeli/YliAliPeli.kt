package YliAliPeli
import kotlin.random.Random
class YliAliPeli(private val min: Int, private val max: Int) {
    private val targetNumber = Random.nextInt(min, max + 1)
    var attempts = 0
    var isGameOver = false

    fun makeGuess(guess: Int): String {
        if (isGameOver) return "Peli on jo päättynyt!"

        attempts++
        return when {
            guess < targetNumber -> "Luku on suurempi!"
            guess > targetNumber -> "Luku on pienempi!"
            else -> {
                isGameOver = true
                "Oikein! Arvasit luvun $targetNumber $attempts yrityksellä."
            }
        }
    }
}

fun main() {
    val peli = YliAliPeli(1, 100)
    println("Tervetuloa pelaamaan Yli-Ali -peliä!")

    while (!peli.isGameOver) {
        print("Arvaa numero: ")
        val input = readLine()?.toIntOrNull()

        if (input != null) {
            println(peli.makeGuess(input))
        } else {
            println("Syötä kelvollinen numero.")
        }
    }
}
