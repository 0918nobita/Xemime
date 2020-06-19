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

class TokenReader(file: File) : Closeable {
    private val randomAccessFile = RandomAccessFile(file, "r")

    var currentLoc = Location(1, 0)
        private set

    override fun close() {
        randomAccessFile.close()
    }

    fun read(): Option<Char> {
        val charCode = randomAccessFile.read()
        if (charCode == -1) return None
        val c = charCode.toChar()
        currentLoc =
            if (c == '\n') { currentLoc.newline() } else { currentLoc.moveRight() }
        return Some(c)
    }

    fun unread() {
        // TODO: adjust currentLoc
        randomAccessFile.seek(-1)
    }
}
