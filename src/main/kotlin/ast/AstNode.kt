package vision.kodai.xemime.ast

interface AstNode<out E> {
    val location: Location
    fun run(): E
}
