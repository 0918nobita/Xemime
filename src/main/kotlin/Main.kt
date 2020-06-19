package vision.kodai.xemime

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("ファイルパスを指定してください")
        exitProcess(1)
    }

    runCatching {
        File(args[0]).inputStream()
    }.fold(
        onSuccess = {
            it.bufferedReader().use { reader ->
                while (true) {
                    val charCode = reader.read()
                    if (charCode == -1) break
                    print(charCode.toChar())
                }
            }
        },
        onFailure = {
            println("ソースファイルの読み込みに失敗しました")
            exitProcess(1)
        }
    )
}
