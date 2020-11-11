var inversionCount = 0

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val input = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    mergeSort(input)

    print(inversionCount)
}

fun mergeSort(array: IntArray) {
    if (array.size < 2) return

    sort(array, 0, array.size - 1)
}

fun sort(array: IntArray, left: Int, right: Int) {
    if (left < right) {
        val middle = (right + left) / 2

        sort(array, left, middle)
        sort(array, middle + 1, right)

        merge(array, left, middle, right)
    }
}

fun merge(array: IntArray, left: Int, middle: Int, right: Int) {
    val arr1 = array.copyOfRange(left, middle + 1)
    val arr2 = array.copyOfRange(middle + 1, right + 1)

    var i = 0
    var j = 0
    var k = left

    while (i < arr1.size && j < arr2.size) {
        if (arr1[i] <= arr2[j]) {
            array[k] = arr1[i]
            i++
        }
        else {
            array[k] = arr2[j]
            j++
            inversionCount += arr1.size - i
        }
        k++
    }

    for (num in i until arr1.size) {
        array[k] = arr1[num]
        k++
    }

    for (num in j until arr2.size) {
        array[k] = arr2[num]
        k++
    }
}