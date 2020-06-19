package vision.kodai.xemime.entity

import arrow.core.Either
import arrow.core.Left
import arrow.core.None
import arrow.core.Option
import arrow.core.Right
import arrow.core.or
import vision.kodai.xemime.ast.Location

class IntEntity(override val location: Option<Location>, private val value: Int) : Entity {
    override fun toString() = "IntEntity[$value]"

    fun add(rhs: Entity, exprLoc: Option<Location> = None): Either<String, IntEntity> {
        if (rhs !is IntEntity) {
            val locMsg = (exprLoc or location).fold({ ".." }, { it.toString() })
            return Left("TypeError ($locMsg)")
        }
        return Right(IntEntity(None, this.value + rhs.value))
    }
}
