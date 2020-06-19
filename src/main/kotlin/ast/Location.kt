package vision.kodai.xemime.ast

data class Location(val row: Int, val col: Int)

fun Location.newline() = Location(this.row + 1, 1)

fun Location.moveLight(offset: Int = 1) = Location(this.row, this.col + offset)
