package vision.kodai.xemime.entity

import arrow.core.Option
import vision.kodai.xemime.ast.Location

class StrEntity(override val location: Option<Location>, private val value: String) : Entity {
    override fun toString() = "StrEntity[\"$value\"]"
}
