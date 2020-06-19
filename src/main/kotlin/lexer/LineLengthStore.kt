package vision.kodai.xemime.lexer

/** 1行目から順番に、各行の文字数を記録する */
class LineLengthStore {
    private val lengths = arrayListOf<Int>()

    fun appendRow(length: Int) {
        lengths.add(length)
    }

    fun maxRowIndex() = lengths.size - 1

    fun getLength(rowIndex: Int) = lengths[rowIndex]
}
