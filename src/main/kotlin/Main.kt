package vision.kodai.xemime

import java.io.File
import kotlin.system.exitProcess
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
    if (args.isEmpty()) {
        println("ファイルパスを指定してください")
        exitProcess(1)
    }

    val src = File(args[0]).readText(Charsets.UTF_8)
    println(src)

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
