package sobornov.dndaigenerator.model

import javax.validation.constraints.Max
import javax.validation.constraints.Min
private const val MIN_ATTRIBUTE = 1L
private const val MAX_ATTRIBUTE = 20L
data class Attributes(
    @field:Min(MIN_ATTRIBUTE) @field:Max(MAX_ATTRIBUTE)
    val strength: Byte,
    @field:Min(MIN_ATTRIBUTE) @field:Max(MAX_ATTRIBUTE)
    val dexterity: Byte,
    @field:Min(MIN_ATTRIBUTE) @field:Max(MAX_ATTRIBUTE)
    val constitution: Byte,
    @field:Min(MIN_ATTRIBUTE) @field:Max(MAX_ATTRIBUTE)
    val intelligence: Byte,
    @field:Min(MIN_ATTRIBUTE) @field:Max(MAX_ATTRIBUTE)
    val wisdom: Byte,
    @field:Min(MIN_ATTRIBUTE) @field:Max(MAX_ATTRIBUTE)
    val charisma: Byte
)
