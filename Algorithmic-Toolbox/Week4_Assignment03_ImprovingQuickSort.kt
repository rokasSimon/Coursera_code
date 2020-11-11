import java.util.*

private val random = Random()

fun partition3(a: IntArray, l: Int, r: Int): Pair<Int, Int> {
    var less = l
    var more  = r
    var i = l

    // Leftmost already chosen as pivot in recursive method
    val pivot = a[l]

    while (i <= more) {
        when {
            a[i] > pivot -> {
                a[i] = a[more].also { a[more] = a[i] }
                more--
            }
            a[i] < pivot -> {
                a[i] = a[less].also { a[less] = a[i] }
                less++
                i++
            }
            else -> i++
        }
    }

    return Pair(less, more)
}

fun partition2(a: IntArray, l: Int, r: Int): Int {
    val x = a[l]
    var j = l
    for (i in l + 1..r) {
        if (a[i] <= x) {
            j++
            val t = a[i]
            a[i] = a[j]
            a[j] = t
        }
    }
    val t = a[l]
    a[l] = a[j]
    a[j] = t
    return j
}

fun randomizedQuickSort(a: IntArray, l: Int, r: Int) {
    if (l >= r) {
        return
    }
    val k = random.nextInt(r - l + 1) + l
    val t = a[l]
    a[l] = a[k]
    a[k] = t
    val (m1, m2) = partition3(a, l, r)
    randomizedQuickSort(a, l, m1 - 1)
    randomizedQuickSort(a, m2 + 1, r)
}

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val input = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    randomizedQuickSort(input, 0, n - 1)

    println(input.joinToString(" "))
}