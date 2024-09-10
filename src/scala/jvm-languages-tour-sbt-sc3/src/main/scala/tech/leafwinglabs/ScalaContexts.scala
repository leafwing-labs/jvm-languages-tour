package tech.leafwinglabs

import scala.util.Random

object ScalaContexts:

  // Scala 2

  // implicits => ordering
  val xs = List("apple", "banana", "cherry", "date", "elderberry")
  xs.sortBy(_.length)
  // List(date, apple, banana, cherry, elderberry)

  object ReverseOrdering extends Ordering[Int]:
    def compare(x: Int, y: Int): Int = y.compareTo(x)

  xs.sortBy(_.length)(ReverseOrdering)
  // List(elderberry, banana, cherry, apple, date)

  
  // Scala 3

  // extensions
  extension (s: String)
    def isPalindrome: Boolean = s == s.reverse

  // using => term inference
  xs.sortBy(_.length)(using ReverseOrdering)

  // given => canonical value
  case class Config(host: String, port: Int)
  given config: Config = Config("localhost", 8080)

