import java.util.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val integers = readLine()!!.split(" ").map { it.toInt() }
    val m = readLine()!!.toInt()
    val queue = ArrayDeque<Int>(m + 1)

    for (i in 0 until n) {
        var reverseIterate = true
        while (reverseIterate) {
            if (!queue.isEmpty() && integers[queue.last] < integers[i]) {
                queue.removeLast()
            }
            else {
                reverseIterate = false
            }
        }
        queue.addLast(i)
        if (queue.first <= i - m) queue.removeFirst()
        if (i >= m - 1) print("${integers[queue.first]} ")
    }
}