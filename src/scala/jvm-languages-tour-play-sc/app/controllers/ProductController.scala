package controllers

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.mvc._

import scala.concurrent.ExecutionContext

case class Product(id: Int, name: String, description: String, price: Double)

@Singleton
class ProductController @Inject()(val cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  import play.api.libs.json._
  implicit val productJson: OFormat[Product] = Json.format[Product]

  private val form: Form[Product] = {
    import play.api.data.Forms._
    import play.api.data.format.Formats._

    Form(
      mapping(
        "id" -> number,
        "name" -> nonEmptyText,
        "description" -> nonEmptyText,
        "price" -> of[Double]
      )(Product.apply)(Product.unapply)
    )
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(Product(1, "Play Framework Essentials", "Learn about Play Framework", 42.0)))
  }
}
