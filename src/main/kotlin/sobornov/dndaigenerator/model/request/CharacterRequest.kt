package sobornov.dndaigenerator.model.request

import jakarta.validation.Valid
import sobornov.dndaigenerator.model.Attributes
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
private const val MIN_LEVEL = 1L
private const val MAX_LEVEL = 20L
data class CharacterRequest(
    val id: String = UUID.randomUUID().toString(),
    val name: String?,
    val specie: Specie?,
    val `class`: CharacterClass,
    @field:Min(MIN_LEVEL) @field:Max(MAX_LEVEL)
    val level: Int,
    @Valid
    val attributes: Attributes?,
    val alignment: Alignment?
)
