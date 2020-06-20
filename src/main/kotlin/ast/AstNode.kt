package vision.kodai.xemime.ast

import arrow.core.Either
import vision.kodai.xemime.entity.Entity

interface AstNode<out E : Entity> {
    val location: Location
    fun run(): Either<String, E>
}
