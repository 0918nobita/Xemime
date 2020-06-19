package vision.kodai.xemime

import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("ファイルパスを指定してください")
        exitProcess(1)
    }

    try {
        val reader = File(args[0])
            .inputStream()
            .bufferedReader()
        reader.use {
            while (true) {
                val charCode = it.read()
                if (charCode == -1) break
                print(charCode.toChar())
            }
        }
    } catch (e: FileNotFoundException) {
        println("指定されたファイルが見つかりません")
    }
}
