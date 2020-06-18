package vision.kodai.xemime

import kotlinx.coroutines.*

fun main(args: Array<String>) {
    println("Start")

    runBlocking {
        delay(1000)
        println("Hello!")
    }

    println("End")
}
