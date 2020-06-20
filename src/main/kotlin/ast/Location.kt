package vision.kodai.xemime.ast

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

data class Location(val filename: Option<String> = None, val row: Int, val col: Int) {
    override fun toString(): String {
        return "$filename:${row + 1}:${col + 1}"
    }
}

fun bof(filename: String) =
    Location(Some(filename), 0, 0)

fun Location.newline() =
    this.copy(row = this.row + 1, col = 0)

fun Location.moveRight(offset: Int = 1) =
    this.copy(col = this.col + offset)
