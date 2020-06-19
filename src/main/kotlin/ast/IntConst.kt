package vision.kodai.xemime.ast

class IntConst(location: Location, val value: Int) : AstNode(location) {
    override fun toString(): String {
        return "IntConst[$value]($location)"
    }
}
