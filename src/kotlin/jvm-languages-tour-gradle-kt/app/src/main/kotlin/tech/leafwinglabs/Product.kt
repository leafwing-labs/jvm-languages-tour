package tech.leafwinglabs

import kotlinx.coroutines.flow.Flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.data.annotation.Id
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter

//data class Product(
//    @Id val id: Int,
//    val name: String,
//    val description: String,
//    val price: Double
//)

interface ProductRepository : CoroutineCrudRepository<Product, Int>

@RestController
class ProductController(private val repo: ProductRepository) {
    @GetMapping("/{id}")
    suspend fun productsById(@PathVariable id: Int): Product =
        Product(1, "Kotlin", "Programming language", 0.0).apply {
            println("Product: $this")
        }
//        this.repo.findById(id) ?: throw IllegalArgumentException("Product not found")
}

@SpringBootApplication
class KotlinSpringFlux

fun main(args: Array<String>) {
    runApplication<KotlinSpringFlux>(*args) {
        addInitializers(beans {
            bean {
                val repo = ref<ProductRepository>()
                coRouter {
                    GET("/products") {
                        val products : Flow<Product> = repo.findAll()
                        ServerResponse.ok().bodyAndAwait(products)
                    }
                }
            }
        })
    }
}