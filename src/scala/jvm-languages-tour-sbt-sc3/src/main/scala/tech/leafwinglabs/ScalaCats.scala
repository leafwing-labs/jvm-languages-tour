import cats.*
import cats.implicits.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MonadTest extends AnyFlatSpec with Matchers:

  // FOR REFERENCE, DOES NOT COMPILE
// implicit def optionMonad(implicit app: Applicative[Option]): Monad[Option] =
//   new Monad[Option] {
//     def pure[A](x: A): Option[A] = app.pure(x)
//
//     def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] =
//       fa.flatMap(f)
//   }

  "Monad Option" should "return value" in:
    Monad[Option].pure(1)shouldBe Some(1)
    Monad[Option].flatMap(Some(1)) {
      x => Some(x + 1)
    } shouldBe Some(2)
    Monad[Option].flatMap(None: Option[Int]) {
      x => Some(x + 1)
    } shouldBe None

  // FOR REFERENCE, DOES NOT COMPILE
// implicit def listMonad(implicit app: Applicative[List]): Monad[List] =
//   new Monad[List] {
//     def pure[A](x: A): List[A] = List(x)
//
//     def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] =
//       fa.flatMap(f)
//   }

  "Monad List" should "return value" in:
    Monad[List].pure(1) shouldBe List(1)
    Monad[List].flatMap(List(1, 2, 3)) {
      x => List(x, x)
    } shouldBe List(1, 1, 2, 2, 3, 3)




