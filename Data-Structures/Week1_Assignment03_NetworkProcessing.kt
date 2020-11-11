import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val (bufferSize, n) = readLine()!!.split(" ").map { it.toInt() }
    val packetQueue = ArrayDeque<Int>(bufferSize)
    val output = StringBuilder()

    for (i in 0 until n) {
        val (startTime, processingTime) = readLine()!!.split(" ").map { it.toInt() }
        var processing = true
        while(processing && !packetQueue.isEmpty()) {
            // if finish time is earlier than start time then finish up packets
            if (packetQueue.peek() <= startTime) {
                packetQueue.remove()
            }
            else {
                processing = false
            }
        }
        when {
            packetQueue.size == 0 -> {
                packetQueue.add(startTime + processingTime)
                output.append("$startTime\n")
            }
            packetQueue.size < bufferSize -> {
                val last = packetQueue.last
                packetQueue.add(last + processingTime)
                output.append("$last\n")
            }
            else -> {
                output.append("-1\n")
            }
        }
    }

    /*
    var finishTime: Int
    if (n == 0) return
    else {
        val (startTime, processingTime) = readLine()!!.split(" ").map { it.toInt() }
        finishTime = startTime + processingTime
        packetQueue.add(finishTime)
        output.append("$startTime\n")
    }

    for (i in 1 until n) {
        val (startTime, processingTime) = readLine()!!.split(" ").map { it.toInt() }
        if (startTime == finishTime) {
            val dequeued = packetQueue.remove()
            finishTime = dequeued.second + processingTime
        }
        if (startTime <= finishTime && packetQueue.size < bufferSize) {
            val lastPacket = packetQueue.peekLast()
            if (lastPacket == null) {
                packetQueue.add(Pair(startTime, finishTime))
                output.append("$startTime\n")
            }
            else {
                packetQueue.add(Pair(lastPacket.second, lastPacket.second + processingTime))
                output.append("${lastPacket.second}\n")
            }
        }
        else if (startTime > finishTime) {
            var catchingUp = true
            while(catchingUp && !packetQueue.isEmpty()) {
                // if finish time is earlier than start time then finish up packets
                if (packetQueue.peek().second < startTime) {
                    packetQueue.remove()
                }
                else {
                    catchingUp = false
                }
            }
            if (packetQueue.size < bufferSize && packetQueue.size != 0) {
                val last = packetQueue.last
                packetQueue.add(Pair(last.second, last.second + processingTime))
                output.append("${last.second}\n")
            }
            else if (packetQueue.size == 0) {
                packetQueue.add(Pair(startTime, startTime + processingTime))
                output.append("$startTime\n")
            }
            else output.append("-1\n")
        }
        else output.append("-1\n")
    }*/

    File("test20.txt").writeText(output.toString())

    print(output.toString())
}