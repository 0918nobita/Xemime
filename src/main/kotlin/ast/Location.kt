package vision.kodai.xemime.ast

data class Location(val row: Int, val col: Int)

fun Location.newline() =
    Location(this.row + 1, 0)

fun Location.moveRight(offset: Int = 1) =
    Location(this.row, this.col + offset)
