package repositories

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

case class Product(id: Int,
                   name: String,
                   description: String,
                   price: Double)

@Singleton
class ProductRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)
                                 (implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class ProductTable(tag: Tag) extends Table[Product](tag, "product") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def description = column[String]("description")

    def price = column[Double]("price")

    def * =
      (id, name, description, price) <>
        ((Product.apply _).tupled, Product.unapply)
  }

  private val products = TableQuery[ProductTable]

  def list(): Future[Seq[Product]] = db run {
    products.result
  }

  def get(id: Int): Future[Option[Product]] = db run {
    products.filter(_.id === id).result.headOption
  }

  def create(product: Product): Unit = db.run(products += product)
}
