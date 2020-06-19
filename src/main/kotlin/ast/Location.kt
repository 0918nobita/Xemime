package vision.kodai.xemime.ast

class Location(val row: Int, val col: Int) {
    override fun toString(): String {
        return "${row + 1}:${col + 1}"
    }
}

val bof = Location(0, 0)

fun Location.newline() =
    Location(this.row + 1, 0)

fun Location.moveRight(offset: Int = 1) =
    Location(this.row, this.col + offset)
