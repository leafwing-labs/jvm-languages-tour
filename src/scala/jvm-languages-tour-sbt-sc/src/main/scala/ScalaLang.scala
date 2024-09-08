import scala.collection.immutable.LazyList

@main def code(): Unit =
  // data
  var name: String = "Scala"
  name = "Scala 3"
  var version: String = "3.5.0"

  // functions
  def add(a: Int, b: Int): Int = a + b
  def add2 = (i: Int) => add.curried(2)(i) // add2(1) = 3
  val add3 = add(3, _: Int) // add3(2) = 5

  // collections
  val lst = List(1, 2, 3)
  val lst2 = List(1, 2, 3) :+ 4 :+ 5
  val lst3 = List("one", "two", "three") ++
    List("four", "five")
  val map: Map[Int, String] = lst2.zip(lst3).toMap
  val seq = Seq(1, 2, 3)

  // stream processing
  lst2.map(_ * 2).filter(_ > 5)                   // List(6, 8, 10)
  map.filter((k, _) => k % 2 == 0)                // Map(2 -> "two", 4 -> "four")
  lst.collect({ case i if i % 2 == 0 => i * 2 })  // List(4, 6)
  lst2.foldLeft(0)(_ + _)                         // 15
  lst2.sum                                        // 15

  // laziness
  lazy val fibs: LazyList[BigInt] =
    BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map { n => n._1 + n._2 }

  println(fibs.takeWhile(_ < 4000000).toList.sum) // 9227463
  println(fibs.dropWhile(_ < 4000000).head) // 5702887