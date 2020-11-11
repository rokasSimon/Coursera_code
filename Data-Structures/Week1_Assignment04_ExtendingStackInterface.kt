import java.lang.StringBuilder
import java.util.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val maxStack = MaxStack()
    val output = StringBuilder()

    for (i in 0 until n) {
        when (val str = readLine()!!) {
            "max" -> output.append("${maxStack.max()}\n")
            "pop" -> maxStack.pop()
            else -> {
                val value = str.split(" ")[1].toInt()
                maxStack.push(value)
            }
        }
    }

    print(output.toString())
}

class MaxStack {
    private val maxStack = Stack<Int>()

    public fun push(value: Int) {
        if (maxStack.empty()) {
            maxStack.push(value)
        }
        else {
            val max = maxStack.peek()
            if (value > max) maxStack.push(value)
            else maxStack.push(max)
        }
    }

    public fun pop() {
        maxStack.pop()
    }

    public fun max(): Int {
        return maxStack.peek()
    }
}