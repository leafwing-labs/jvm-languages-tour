import scala.util.Try

// case classes
case class Product(name: String, description: String, price: Double = 0.0) {
  def discount(discount: Double): Double = price - price * discount
}

val p = Product("Scala", "Programming Language")
val p2 = Product(description = "Programming Language", name = "Scala")
val p3 = Product.apply("Scala", "Programming Language", 10)
val p4 = p3.copy(price = 20)
val p5 = Product(p3._1, p3._2, p._3)

// sealed traits
sealed trait Color

case object Red extends Color

case object Green extends Color

case object Blue extends Color

// pattern matching
def blendColors(color: Color, weight: Int): String = color match {
  case Red if weight % 2 == 0 => weight * 2
  case Red => weight * 3
  case Green => {
    println("Processing green")
    weight - 1
  }
  case Blue => Math.pow(weight, 2).toInt
}

// contextual abstraction
object IntExtensions {
  extension (i: Int)
    def square: Int = i * i
    def reverse: Int = i.toString.reverse.toInt
}

// given => declare context
given intExtensions: IntExtensions.type = IntExtensions
def squares(i: Int)(using ie: IntExtensions.type) =
  LazyList.iterate(i)(x => x + 1).map(_.square)

// using => declare implicit use
def main2()(using ie: IntExtensions.type): Unit = {
  for (i <- squares(1).map(_.reverse).take(5)) {
    println(i)
  } // 1, 4, 9, 61, 52
}

// implicit conversion
given Conversion[Int, String] = _.toString
given conversion[A, B]: Conversion[A, B] = new Conversion[A, B] {
  override def apply(a: A): B = a.toString()
}


// request
case class Request(method: String, path: String)

case class RequestConfig(port: Int, baseUrl: String)

given RequestConfig = RequestConfig(8080, "localhost")

def `do`(request: Request)(using config: RequestConfig): Unit = {
  println(s"Request to ${config.baseUrl}:${config.port}")
  Try {
    // do something
  } recover {
    case e: IllegalStateException => println(s"State Issue: $e")
    case e: Exception => println(e)
  }
}



