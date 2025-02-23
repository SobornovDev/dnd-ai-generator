package sobornov.dndaigenerator.model.response

import sobornov.dndaigenerator.model.Attributes

data class CharacterResponse(
    val requestId: String,
    val responseId: String,
    val generatedBackstory: String,
    val combatAbilities: Map<String, String>?,
    val spells: Map<String, String?>?,
    val dndStats: Attributes?,
    val aiGeneratedQuote: String?,
    val executionTime: String?
)
