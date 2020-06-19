package vision.kodai.xemime.lexer

import java.io.Closeable
import java.io.File
import java.io.RandomAccessFile

import vision.kodai.xemime.ast.Location
import vision.kodai.xemime.ast.moveLight
import vision.kodai.xemime.ast.newline

class TokenReader(file: File) : Closeable {
    private val randomAccessFile = RandomAccessFile(file, "r")

    var currentLoc = Location(1, 0)
        private set

    override fun close() {
        randomAccessFile.close()
    }

    // TODO: Use option type
    fun read(): Char? {
        val charCode = randomAccessFile.read()
        if (charCode == -1) return null
        val c = charCode.toChar()
        currentLoc =
            if (c == '\n') { currentLoc.newline() } else { currentLoc.moveLight() }
        return c
    }

    fun unread() {
        // TODO: adjust currentLoc
        randomAccessFile.seek(-1)
    }
}
