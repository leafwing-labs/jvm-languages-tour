package tech.leafwinglabs

import scala.collection.immutable.LazyList

object Main extends App:

  // data
  var name: String = "Scala"
  name = "Scala 3"                // mutable
  val version = "3"               // immutable
  s"Hello, $name!"                // string interpolation

  // collections => stream processing
  val lst = List(1, 2, 3, 4, 5)
  val map = Map(1 -> "one", 2 -> "two", 3 -> "three")
  val seq = List("1", "2", "3", "4", "5")
    .map(_.toInt)
    .filter(_ % 2 == 0)
    .map(x => x * x)
    .sortBy(_.toString.reverse)
    .map(_.toChar.toString)
    .mkString("|")

  // higher-order functions
  def consumer[T](fn: T => Unit, x: T): Unit =
    println(s"Consuming function: ${fn.getClass.getSimpleName}")
    fn(x)

  val greet2: Int => String = greet.curried("Scala") // Currying
  val greet3: String => String = greet(_, 3) // Partial application

  // extension functions => implicits
  implicit class StringOps(s: Option[String]):
    def isNullOrEmpty: Boolean = s.isEmpty || s.exists(_.isBlank)


  // monads
  def shout(s: Option[String]): Option[String] =
    s.filter(_.endsWith("!")).map(_.toUpperCase)

  println(shout(Some("Scala")))      // None
  println(shout(Some("scala!!!")))   // Some(SCALA!!!)

  // destructure
  def stats(xs: List[Int]): (Int, Int) =
    (xs.sum, xs.sum / xs.size)

  val (a, b) = stats(List(1, 2, 3, 4, 5))
  println(a)                    // 15
  println(b)                    // 3

  // case classes => tuple operations
  case class Pair[T, U](a: T, b: U)
  type SumAvg = Pair[Int, Int]
  val (sum, avg) = stats(List(1, 2, 3, 4, 5))

  def score(stats: SumAvg): String =
    stats match
      case Pair(s, a) if s >= 20 && a >= 4 => s"High score: $s"
      case Pair(s, a) if s < 15 && a < 3 => s"Low score: $s"
      case Pair(s, _) => s"Average score: $s"

  println(score(Pair(sum, avg))) // Average score: 15

  // pattern matching => partial functions
  def matcher = (t: Any) =>
    t match
      case s: String if !s.isBlank => s.toUpperCase
      case i: Int => i * 2
      case it: Iterable[_] => it.size
      case _ => "Unknown"
  println(matcher("Scala")) // SCALA
  println(matcher("")) // Unknown
  println(matcher(2)) // 4
  println(matcher(List(1, 2, 3))) // 3

  // functions
  def greet(id: String = "World", n: Int = 1): String =
    s"Hello, $id!".repeat(n)

  greet()
  greet("Eric")
  greet(n = 3)
  greet(n = 5, id = "Eric")

  // function expressions
  def goodbye(id: String = "World", n: Int = 1): String =
    s"Goodbye, $id!".repeat(n)

  // callables
  val rng = 1 to 100
  rng.reduce(_ + _)

  def odd(n: Int): Boolean = n % 2 == 0
  rng.filter(odd).foreach(log)

  // scope functions => Object context
  def finalMessage(s: String): String =
    s.length match
      case x if x < 20 => s.toUpperCase() + "!!!"
      case _ => """
        |Too much effort to say
        |Try again later""".stripMargin

  finalMessage(goodbye()) // GOODBYE, WORLD!!!
  finalMessage(goodbye("Cruel World")) // Too much effort to say\nTry again later

// Classes
trait Talker:
  def talk(): String
  def log(): Unit = println(talk())

class Greeter extends Talker:
  var id: String = scala.compiletime.uninitialized

  override def talk(): String = "Hello, Scala!"

object Greeter:
  private val greeter: Greeter = new Greeter()
  def create(): Greeter = greeter
  def upperCase(): String = greeter.talk().toUpperCase

// Log abstraction
def log(obj: Any): Unit =
  println(obj)

// Data
case class RequestStatus(status: Int, message: String)

// Open => Enrichment
class RequestFilter:
  def filter[T](request: Request[T]): Boolean = true

class CacheRequestFilter extends RequestFilter:
  override def filter[T](request: Request[T]): Boolean = request.isInstanceOf[CacheRequest[?]]

// Sealed => exhaustive middleware
sealed abstract class Request[+T]:
  def `do`(): RequestStatus
  def middleware(): RequestStatus =
    this match
      case cacheRequest: CacheRequest[_] =>
        log(s"Cache Request: ${cacheRequest.data}")
      case recRequest: RecRequest =>
        log(s"Recommendation Request: ${recRequest.serviceName}")
      case _ =>
        log("Unmanaged Service Request")
    `do`()

// Mock
case class MockRequest(id: String) extends Request[MockRequest]:
  override def `do`(): RequestStatus = RequestStatus(200, "Mock Request Success")

// Cache adapter
case class CacheRequest[T](data: T) extends Request[T]:
  override def `do`(): RequestStatus = RequestStatus(200, "Cache Request Success")

// Recommendation adapter
case class Recommendation(id: String)
case class RecRequest(serviceName: String) extends Request[Recommendation]:
  override def `do`(): RequestStatus = RequestStatus(200, "Recommendation Request Success")
