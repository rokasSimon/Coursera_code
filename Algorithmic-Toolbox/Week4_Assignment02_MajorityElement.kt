fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val input = readLine()!!.split(" ").map { it.toLong() }

    print(BoyerMooreAlgorithm(input))
}

fun BoyerMooreAlgorithm(longArray: List<Long>): Int {
    var count = 0
    var element: Long = 0
    for (x in longArray) {
        when {
            count == 0 -> {
                element = x
                count = 1
            }
            element == x -> {
                count++
            }
            else -> {
                count--
            }
        }
    }

    count = 0
    for (x in longArray) {
        if (x == element) count++
    }

    if (count > longArray.size / 2) {
        return 1
    }
    return 0
}