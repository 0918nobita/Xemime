package vision.kodai.xemime.ast

import arrow.core.Some
import vision.kodai.xemime.entity.IntEntity

class IntConst(override val location: Location, private val value: Int) : AstNode {
    override fun run() = IntEntity(Some(location), value)

    override fun toString(): String {
        return "IntConst[$value]($location)"
    }
}
