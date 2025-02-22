package sobornov.rate_limiter.model

import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class Attributes(
    @field:Min(1) @field:Max(20)
    val strength: Int,
    @field:Min(1) @field:Max(20)
    val dexterity: Int,
    @field:Min(1) @field:Max(20)
    val constitution: Int,
    @field:Min(1) @field:Max(20)
    val intelligence: Int,
    @field:Min(1) @field:Max(20)
    val wisdom: Int,
    @field:Min(1) @field:Max(20)
    val charisma: Int
)