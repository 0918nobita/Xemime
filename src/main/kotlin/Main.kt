package vision.kodai.xemime

import arrow.core.None
import arrow.core.getOrElse
import java.io.File
import kotlin.system.exitProcess
import vision.kodai.xemime.ast.AstNode
import vision.kodai.xemime.ast.IntConst
import vision.kodai.xemime.ast.bof
import vision.kodai.xemime.entity.Entity
import vision.kodai.xemime.lexer.CharReader

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("ファイルパスを指定してください")
        exitProcess(1)
    }

    val file = File(args[0])
    if (!file.exists()) {
        println("指定されたソースファイルが存在しません")
        exitProcess(1)
    }

    val reader = CharReader(file)
    reader.use {
        it.read().fold(ifEmpty = {
            println("empty")
        }, ifSome = { c ->
            val msg = if (c == '\n') "[br]" else c.toString()
            println("char: $msg, loc: ${it.currentLoc}")
        })
    }

    val ast: AstNode<Entity> = IntConst(bof(None), 123)
    val value = ast.run() // value: Entity
    println("$value, ${value.location.getOrElse { "empty" }}")
}
