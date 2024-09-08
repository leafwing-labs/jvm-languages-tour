package tech.leafwinglabs

typealias MinMax = Pair<Int, Int>

// classic iteration
fun minMax1(xs: List<Int>): MinMax {
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    for (x in xs) {
        if (x < min) min = x
        if (x > max) max = x
    }
    return MinMax(min, max)
}

// collection api
fun minMax2(xs: List<Int>): MinMax {
    val min = xs.minOrNull() ?: Int.MAX_VALUE
    val max = xs.maxOrNull() ?: Int.MIN_VALUE
    return MinMax(min, max)
}

// reduce => accumulate starting at first value
fun minMax3(xs: List<Int>): MinMax =
    MinMax(
        xs.reduce { acc, x -> minOf(acc, x) },
        xs.reduce { acc, x -> maxOf(acc, x) }
    )

// fold => accumulate starting at seed value
fun minMax4(xs: List<Int>): MinMax =
    xs.fold(MinMax(Int.MAX_VALUE, Int.MIN_VALUE)) { (min, max), x ->
        MinMax(
            minOf(min, x),
            maxOf(max, x)
        )
    }