import java.lang.StringBuilder

fun main(args: Array<String>) {
    val (nSeg, nPoint) = readLine()!!.split(" ").map { it.toInt() }
    val points = arrayOfNulls<Point>(nPoint + 2 * nSeg)

    for (i in 0 until nSeg * 2 step 2) {
        val (a, b) = readLine()!!.split(" ").map { it.toLong() }
        points[i] = Point(a, 'l')
        points[i + 1] = Point(b, 'r')
    }

    val pointArray = readLine()!!.split(" ").map { it.toLong() }
    val indices = HashMap<Long, ArrayList<Int>>(50000)

    var i = nSeg * 2
    for (x in pointArray.indices) {
        points[i] = Point(pointArray[x], 'p')
        if (indices[pointArray[x]] != null) {
            indices[pointArray[x]]!!.add(x)
        }
        else {
            indices[pointArray[x]] = arrayListOf(x)
        }
        i++
    }

    points.sort()
    var currentSize = 0
    val counts = IntArray(nPoint)

    for (j in points.indices) {
        when (points[j]!!.type) {
            'p' -> {
                val prev = indices[points[j]!!.x]!!

                for (index in prev) {
                    counts[index] = currentSize
                }
            }
            'l' -> { currentSize++ }
            'r' -> { currentSize-- }
        }
    }

    print(counts.joinToString(" "))
}

data class Point(val x: Long, val type: Char): Comparable<Point> {
    override fun compareTo(other: Point): Int {
        if (this.x < other.x || this.x == other.x && this.type < other.type) {
            return -1
        }
        else if (this.x == other.x && this.type == other.type) {
            return 0
        }
        return 1
    }
}