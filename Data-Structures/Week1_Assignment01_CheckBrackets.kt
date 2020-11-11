import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data class Bracket(val type: Char, val column: Int)

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val text: String = reader.readLine()

    val openingBracketsStack = Stack<Bracket>();

    for ((index, value) in text.withIndex()) {
        when (value) {
            '(', '{', '[' -> openingBracketsStack.push(Bracket(value, index + 1))
            ')', '}', ']' -> {
                val bracket = if (openingBracketsStack.empty()) {
                    print(index + 1)
                    return
                } else openingBracketsStack.pop()
                if (!(value - 2 == bracket.type || value - 1 == bracket.type)) {
                    print(index + 1)
                    return
                }
            }
        }
    }

    if (openingBracketsStack.empty()) print("Success") else print(openingBracketsStack.firstElement().column)
}

