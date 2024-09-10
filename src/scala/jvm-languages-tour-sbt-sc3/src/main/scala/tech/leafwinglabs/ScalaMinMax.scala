package tech.leafwinglabs

type MinMax = (Int, Int)

// classic for
def minMax1(xs: List[Int]): MinMax =
  var p = Int.MaxValue
  var q = Int.MinValue
  for x <- xs do
    p = p min x
    q = q max x
  (p, q)

// fold
def minMax2(xs: List[Int]): MinMax =
  (xs.fold(Int.MaxValue)(_ min _), xs.fold(Int.MinValue)(_ max _))

// reduce
def minMax3(xs: List[Int]): MinMax =
  (xs.reduce(_ min _), xs.reduce(_ max _))

// convenience
def minMax4(xs: List[Int]): MinMax = (xs.min, xs.max)

