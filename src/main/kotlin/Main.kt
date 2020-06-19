package vision.kodai.xemime

import java.io.File
import kotlin.system.exitProcess
import vision.kodai.xemime.lexer.TokenReader

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

    val reader = TokenReader(file)
    reader.use {
        println(it.currentLoc)
        for (i in 0..34) {
            it.read().fold(ifEmpty = {
                println("empty")
            }, ifSome = { c ->
                println("char: $c, loc: ${it.currentLoc}")
            })
        }
    }
}
