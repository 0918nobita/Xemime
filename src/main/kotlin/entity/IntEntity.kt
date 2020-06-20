package vision.kodai.xemime.entity

import arrow.core.Either
import arrow.core.Left
import arrow.core.None
import arrow.core.Option
import arrow.core.Right
import vision.kodai.xemime.ast.Location

class IntEntity(override val location: Option<Location>, private val value: Int) : Entity {
    override fun toString() = "IntEntity[$value]"

    fun add(rhs: Entity): Either<String, IntEntity> =
        if (rhs !is IntEntity) {
            Left("type error")
        } else {
            Right(IntEntity(None, this.value + rhs.value))
        }
}
