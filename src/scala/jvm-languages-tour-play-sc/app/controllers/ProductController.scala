package controllers

import play.api.data.Form
import play.api.mvc._
import repositories.{Product, ProductRepository}

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProductController @Inject()(val productRepository: ProductRepository,
                                  val cc: ControllerComponents)
                                 (implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  import play.api.libs.json._
  implicit val productJson: OFormat[Product] = Json.format[Product]

  def list() = Action.async { implicit request =>
    productRepository.list().map { products =>
      Ok(Json.toJson(products))
    }
  }

  def get(id: Int) = Action.async { implicit request =>
    productRepository.get(id).map {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound
    }
  }

  val productForm = {
    import play.api.data.Forms._
    import play.api.data.format.Formats.doubleFormat
    Form(
      mapping(
        "id" -> number,
        "name" -> text,
        "description" -> text,
        "price" -> of(doubleFormat)
      )(Product.apply)(Product.unapply)
    )
  }

  def create = Action.async { implicit request =>
    productForm.bindFromRequest.fold(
      errorForm => {
        Future(BadRequest("Invalid data"))
      },
      product => {
        productRepository.create(product)
        Future.successful(Created)
      }
    )
  }

}
