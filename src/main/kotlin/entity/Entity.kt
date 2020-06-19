package vision.kodai.xemime.entity

import arrow.core.Option
import vision.kodai.xemime.ast.Location

interface Entity {
    val location: Option<Location>
}
