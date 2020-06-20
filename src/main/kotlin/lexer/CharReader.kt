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

    private val lineLengthStore = LineLengthStore()

    private var position: Long = 0

    var currentLoc = Location(Some(file.absolutePath), 0, 0)
        private set

    override fun close() {
        randomAccessFile.close()
    }

    fun read(): Option<Char> {
        val charCode = randomAccessFile.read()
        if (charCode == -1) return None
        val c = charCode.toChar()
        position++
        currentLoc =
            if (c == '\n') {
                if (currentLoc.row > lineLengthStore.maxRowIndex())
                    lineLengthStore.appendRow(currentLoc.col)
                currentLoc.newline()
            } else {
                currentLoc.moveRight()
            }
        return Some(c)
    }

    fun unread() {
        currentLoc =
            if (currentLoc.col == 0) {
                val newRow = currentLoc.row - 1
                currentLoc.copy(row = newRow, col = lineLengthStore.getLength(newRow) - 1)
            } else {
                currentLoc.copy(col = currentLoc.col - 1)
            }

        position--
        randomAccessFile.seek(position)
    }
}
