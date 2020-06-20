package vision.kodai.xemime

import arrow.core.None
import java.io.File
import kotlin.system.exitProcess
import vision.kodai.xemime.ast.AddExpr
import vision.kodai.xemime.ast.IntConst
import vision.kodai.xemime.ast.bof
import vision.kodai.xemime.lexer.CharReader

fun main(args: Array<String>) {
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
}
