package vision.kodai.xemime

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun foo(): Flow<Int> = flow {
    repeat(10) {
        delay(500)
        emit(it)
    }
}

fun main(args: Array<String>) {
    val result = Result.success(10)
    println(result.map { it * 2 }.getOrDefault(0))

    println("Start")

    runBlocking {
        foo().collect {
            println(it)
        }
    }

    println("End")
}
