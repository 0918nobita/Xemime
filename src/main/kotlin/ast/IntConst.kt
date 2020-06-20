package vision.kodai.xemime.ast

import arrow.core.Right
import arrow.core.Some
import vision.kodai.xemime.entity.IntEntity

class IntConst(override val location: Location, private val value: Int) : AstNode<IntEntity> {
    override fun run() = Right(IntEntity(Some(location), value))

    override fun toString(): String {
        return "IntConst[$value]($location)"
    }
}
