//import $ivy.`com.github.tototoshi:scala-csv_2.13:1.3.8`
//import scala.io.Source
//import com.github.tototoshi.csv._

//case class Product(
//  id: Int,
//  name: String,
//  description: String,
//  price: Double
//)
//
//@main def processCsvData(csvFilePath: String): Unit = {
//  val reader = CSVReader.open(Source.fromFile(csvFilePath))
//  val products: List[Product] = reader.allWithHeaders().map { row =>
//    Product(row("id").toInt, row("name"), row("description"), row("price").toDouble)
//  }
//  reader.close()
//  products.foreach(println)
//}
//

