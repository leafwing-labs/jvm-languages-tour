package tech.leafwinglabs

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double
)

fun main() {
    val products = mutableListOf(
        Product(1, "Product 1", "Product 1 description", 100.0),
        Product(2, "Product 2", "Product 2 description", 200.0),
        Product(3, "Product 3", "Product 3 description", 300.0)
    )

    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/products") {
                call.respond(products)
            }
            get("/products/{id}") {
                val id = call.parameters["id"]?.toInt()
                val product = products.find { it.id == id }
                if (product != null) {
                    call.respond(product)
                } else {
                    call.respond("Product not found")
                }
            }
            post("/products") {
                val product = call.receive<Product>()
                products.add(product)
                call.respond("Product added")
            }
        }
    }
    server.start(wait = true)
}