package sobornov.dndaigenerator.model.request

import jakarta.validation.Valid
import sobornov.dndaigenerator.model.Attributes
import java.util.*

data class CharacterRequest(
    val id: String = UUID.randomUUID().toString(),
    val name: String?,
    val specie: Specie?,
    val `class`: CharacterClass,
    @Valid
    val attributes: Attributes?,
    val alignment: Alignment?
)
