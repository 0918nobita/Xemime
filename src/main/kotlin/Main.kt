package vision.kodai.xemime

import arrow.core.None
import arrow.core.Tuple2
import arrow.mtl.State
import arrow.mtl.flatMap
import arrow.mtl.run
import java.io.File
import javafx.application.Application
import kotlin.system.exitProcess
import vision.kodai.xemime.ast.AddExpr
import vision.kodai.xemime.ast.IntConst
import vision.kodai.xemime.ast.bof
import vision.kodai.xemime.lexer.CharReader

fun main(args: Array<String>) {
    val state = State<Int, Int> { Tuple2(it + 1, it * 2) }
    val state2 = state.flatMap { state }
    println(state2.run(1)) // (3, 4)

    if (args.isEmpty()) {
        println("ファイルパスを指定してください")
        exitProcess(1)
    }

    val file = File(args[0])

    val uncheckedFile = SourceFile.create(file)

    uncheckedFile.check().fold(
        ifLeft = {
            println("指定されたソースファイルが存在しません: $it")
            exitProcess(1)
        },
        ifRight = { existingFile ->
            val reader = CharReader(existingFile)
            reader.use {
                it.read().fold(ifEmpty = {
                    println("empty")
                }, ifSome = { c ->
                    val msg = if (c == '\n') "[br]" else c.toString()
                    println("char: $msg, loc: ${it.currentLoc}")
                })
            }
        }
    )

    val lhs = IntConst(bof(None), 3)
    val rhs = IntConst(bof(None), 4)
    val ast = AddExpr(bof(None), lhs, rhs)
    ast.run().fold(
        ifLeft = {
            println("Error: $it")
        },
        ifRight = {
            println("Result: $it")
        }
    )

    Application.launch(Window::class.java, *args)
}
