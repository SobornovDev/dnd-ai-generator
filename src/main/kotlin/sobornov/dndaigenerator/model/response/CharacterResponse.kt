package sobornov.dndaigenerator.model.response

import sobornov.dndaigenerator.model.Attributes

data class CharacterResponse(
    val responseId: String,
    val generatedBackstory: String,
    val combatAbilities: List<String>,
    val dndStats: Attributes,
    val aiGeneratedQuote: String,
    val executionTime: String
)
