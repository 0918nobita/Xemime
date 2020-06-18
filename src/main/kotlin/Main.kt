package vision.kodai.xemime

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import vision.kodai.xemime.ast.Location

fun foo(): Flow<Int> = flow {
    repeat(10) {
        delay(500)
        emit(it)
    }
}

fun main(args: Array<String>) {
    val result = Result.success(10)
    println(result.map { it * 2 }.getOrDefault(0))

    val loc = Location(18, 24)
    println(loc)

    println("Start")

    runBlocking {
        foo().collect {
            println(it)
        }
    }

    println("End")
}
