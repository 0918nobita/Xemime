package vision.kodai.xemime

import java.io.File
import kotlin.reflect.KProperty
import kotlin.system.exitProcess
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

import vision.kodai.xemime.ast.Location

object Delegate {
    var count = -1

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        count++
        return count
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        count = value
    }
}

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

    var property by Delegate
    println("(1) $property")
    println("(2) $property")
    property = 100
    println("(3) $property")


    val result = Result.success(10)
    println("Result: ${result.map { it * 2 }.getOrDefault(0)}")

    println("Start")

    runBlocking {
        foo().collect {
            println(it)
        }
    }

    println("End")
}
