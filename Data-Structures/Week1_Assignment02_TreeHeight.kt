import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val intArray = readLine()!!.split(" ").map { it.toInt() }
    var root = 0
    val nodes = Array<ArrayList<Int>>(n) { ArrayList() }

    for (index in intArray.indices) {
        if (intArray[index] == -1) root = index
        else nodes[intArray[index]].add(index)
    }

    val queue: Queue<Int> = ArrayDeque()
    queue.add(root)
    var maxHeight = 0

    while(queue.isNotEmpty()) {
        maxHeight++
        var currentLevelNodeCount = queue.size
        while(currentLevelNodeCount > 0) {
            val node = queue.remove()
            for (subNode in nodes[node]) {
                queue.add(subNode)
            }
            currentLevelNodeCount--
        }
    }

    print(maxHeight)
}

/*fun generateTree(n: Int, intArray: List<Int>) {
    val nodes = MutableList(n) { _ -> Node(1) }
    var rootIndex = 0

    for (index in intArray.indices) {
        if (intArray[index] == -1) {
            rootIndex = index
        }
        else {
            nodes[intArray[index]].subNodes.add(nodes[index])
        }
    }

    print(calculateHeightRecursive(nodes[rootIndex]))
}

fun calculateHeight(root: Node, n: Int) {
    val queue: Queue<Node> = ArrayDeque(n)
    queue.add(root)
    var maxHeight = 1

    while (queue.isNotEmpty()) {
        val current = queue.remove()
        if (current.subNodes.isNotEmpty()) {
            current.subNodes.forEach {node ->
                node.height = current.height + 1
                if (node.height > maxHeight) maxHeight = node.height
                queue.add(node)
            }
        }
    }

    print(maxHeight)
}

fun calculateHeightRecursive(root: Node): Int {
    var maxHeight = 0
    for (node in root.subNodes) {
        maxHeight = maxHeight.coerceAtLeast(calculateHeightRecursive(node))
    }

    return maxHeight + 1
}

class Node(var height: Int) {
    public val subNodes = mutableListOf<Node>()
}*/