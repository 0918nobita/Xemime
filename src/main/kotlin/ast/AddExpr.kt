package vision.kodai.xemime.ast

import arrow.core.Either
import arrow.core.extensions.fx
import vision.kodai.xemime.entity.Entity

class AddExpr<L : Entity, R : Entity>(
    override val location: Location,
    private val lhs: AstNode<L>,
    private val rhs: AstNode<R>
) : AstNode<Entity> {
    override fun run(): Either<String, Entity> =
        Either.fx {
            val lhsEntity = !lhs.run()
            val rhsEntity = !rhs.run()
            !lhsEntity.add(rhsEntity)
        }
}
