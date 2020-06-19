package vision.kodai.xemime.ast

import vision.kodai.xemime.entity.Entity

interface AstNode {
    val location: Location
    fun run(): Entity
}
