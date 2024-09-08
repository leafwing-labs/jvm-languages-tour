package tech.leafwinglabs

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.TestOnly
import org.slf4j.LoggerFactory
import kotlin.random.Random

fun main() {

    // data
    var name: String = "Kotlin"
    name = "Kotlin 1.5.21"          // mutable
    val version = "1.5.21"          // immutable
    val t2: String? = null          // null checking
    t2?.length                      // null safety

    // type-checking
    "1" is String                   // true
    '1' !is Char                    // false
    "1" as CharSequence             // correct
    "1" as Int?                     // exception
    "1" as? Int                     // null

    // smart casts
    val t3: Any = "nonce"
    when (t3) {
        is String -> print("Length: ${t3.length}")
        is Int -> print("Increment: ${t3.inc()}")
        else -> print("Unknown type")
    }

    // collections => stream processing
    val lst = listOf(1, 2, 3, 4, 5)
    val map = mapOf(1 to "one", 2 to "two", 3 to "three")
    val seq = sequenceOf("1", "2", "3", "4", "5")
        .map(String::toInt)
        .filter { n -> n % 2 == 0 }
        .map { it * it }
        .sortedByDescending { it.toString() }
        .joinToString(separator = "|") { it.toChar().toString() }

    // functions
    fun greet(id: String = "World", n: Int = 1): String {
        return "Hello, $id!".repeat(n)
    }
    greet()
    greet("Eric")
    greet(n = 3)
    greet(n = 5, id = "Eric")

    // function expressions
    fun goodbye(id: String = "World", n: Int = 1): String =
        "Goodbye, $id!".repeat(n)

    // callables
    val rng = 1..100
    rng.reduce(Int::plus)

    fun odd(n: Int) = n % 2 == 0
    rng.filter(::odd).forEach(::log)

    // bounded callables
    val logger = LoggerFactory.getLogger("KotlinLang")
    (1..Random.nextInt(100))
        .map { it.toString() }
        .forEach(logger::info)

    // higher-order functions
    fun <T> consumer(fn: (T) -> Unit, x: T) {
        println("Consuming function: ${fn.javaClass.simpleName}")
        fn(x)
    }

    val greet3: (String) -> String = { id: String -> greet(id, 3) }   // partial application

    // extension functions
    fun String?.isNullOrEmpty(): Boolean {
        return this == null || this.isEmpty()
    }

    // scope functions => object context
    fun finalMessage(s: String) = s.takeIf {
        it.length < 20
    }?.let {
        it.uppercase() + "!!!"
    }?: """
        |Too much effort to say
        |Try again later
    """.trimIndent()

    finalMessage(goodbye())                       // GOODBYE, WORLD!!!
    finalMessage(goodbye("Cruel World"))      // Too much effort to say\nTry again later

}



// classes
interface Talker {
    fun talk(): String
    @TestOnly
    fun log(): Unit = println(talk())
}

class Greeter : Talker {
    lateinit var id: String

    override fun talk(): String {
        return "Hello, Kotlin!"
    }

    companion object {
        val greeter: Greeter = Greeter()
        @JvmStatic fun create(): Greeter = greeter
        @JvmStatic fun upperCase(): String = greeter.talk().uppercase()
    }
}

// log abstraction
fun log(obj: Any) {
    println(obj)
}

// data => what happens during a request??
data class RequestStatus(
    val status: Int,
    val message: String
)

// open => enrichment
open class RequestFilter {
    open fun filter(request: Request<*>): Boolean = true
}

class CacheRequestFilter : RequestFilter() {
    override fun filter(request: Request<*>): Boolean = request is CacheRequest<*>
}

// sealed => exhaustive middleware
sealed class Request<T> {
    abstract fun `do`(): RequestStatus
    fun middleware(): RequestStatus {
        when (this) {
            is CacheRequest<*> -> log("Cache Request: ${this.data}")
            is RecRequest -> log("Recommendation Request: ${this.serviceName}")
            else -> log("Unmanaged Service Request")
        }
        return this.`do`()
    }
}

// mock
data class MockRequest(val id: String) : Request<MockRequest>() {
    override fun `do`(): RequestStatus {
        return RequestStatus(200, "Mock Request Success")
    }
}

// cache adapter
data class CacheRequest<T>(val data: T) : Request<T>() {
    override fun `do`(): RequestStatus {
        return RequestStatus(200, "Cache Request Success")
    }
}

// recommendation adapter
data class Recommendation(val id: String)
data class RecRequest(val serviceName: String) : Request<Recommendation>() {
    override fun `do`(): RequestStatus {
        return RequestStatus(200, "Recommendation Request Success")
    }
}



// coroutines
interface SendChannel<in E> {
    suspend fun send(element: E)
    fun close(): Boolean
}
interface ReceiveChannel<out E> {
    suspend fun receive(): E
}
interface Channel<E> : SendChannel<E>, ReceiveChannel<E>

// channels and structured concurrency
fun channels() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        channel.close()
    }
    for (y in channel) println(y)
}