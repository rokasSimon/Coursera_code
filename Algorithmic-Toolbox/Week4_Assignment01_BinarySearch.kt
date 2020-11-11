import java.lang.StringBuilder

fun main(args: Array<String>) {
    val sortedArray = readLine()!!.split(" ").map { it.toInt() }
    val searchArray = readLine()!!.split(" ").map { it.toInt() }

    val output = StringBuilder(searchArray.size * 2)

    for (i in 1 until searchArray.size) {
        if (searchArray[i] > sortedArray[sortedArray.size - 1] || searchArray[i] < sortedArray[1]) output.append("-1 ")
        else output.append(binarySearch(sortedArray, searchArray[i]).toString() + " ")
    }
    
    print(output.toString())
}

fun binarySearch(array: List<Int>, searchFor: Int): Int {
    var low  = 1
    var high = array.size - 1

    while (low <= high) {
        val mid: Int = (high + low) / 2

        when {
            array[mid] == searchFor -> return mid - 1
            array[mid] < searchFor -> {
                low = mid + 1
            }
            else -> {
                high = mid - 1
            }
        }
    }

    return -1
}