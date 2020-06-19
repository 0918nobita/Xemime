package vision.kodai.xemime.lexer

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import java.io.Closeable
import java.io.File
import java.io.RandomAccessFile
import vision.kodai.xemime.ast.Location
import vision.kodai.xemime.ast.moveRight
import vision.kodai.xemime.ast.newline

/** テキストファイルの内容を1文字ずつ読む */
class CharReader(file: File) : Closeable {
    private val randomAccessFile = RandomAccessFile(file, "r")

    /** 行ごとの文字数 */
    private val lineLengthMap: ArrayList<Int> = ArrayList()

    // TODO: Simplify representation of current location
    /** 現在位置 (改行も1文字として数える) */
    private var position: Long = -1

    /** 現在位置 (行数 + 左から数えた文字数) */
    var currentLoc = Location(0, 0)
        private set

    override fun close() {
        randomAccessFile.close()
    }

    /** 次の1文字を読む */
    fun read(): Option<Char> {
        val charCode = randomAccessFile.read()
        if (charCode == -1) return None
        val c = charCode.toChar()
        position++
        currentLoc =
            if (c == '\n') {
                if (currentLoc.row > lineLengthMap.size - 1) lineLengthMap.add(currentLoc.col)
                currentLoc.newline()
            } else {
                currentLoc.moveRight()
            }
        return Some(c)
    }

    /** 1文字戻る */
    fun unread() {
        currentLoc =
            if (currentLoc.col == 0) {
                val newRow = currentLoc.row - 1
                Location(newRow, lineLengthMap[newRow] - 1)
            } else {
                Location(currentLoc.row, currentLoc.col - 1)
            }

        position--
        randomAccessFile.seek(position)
    }
}
