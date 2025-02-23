package sobornov.dndaigenerator.model.response.dndapi

import com.fasterxml.jackson.annotation.JsonProperty

data class SpellcastingAbility(
    val index: String?,
    val name: String?,
    val url: String?
)

data class SpellInfo(
    val name: String?,
    val desc: List<String>?
)

data class SpellcastingInfo(
    val level: Int?,
    @JsonProperty("spellcasting_ability")
    val spellcastingAbility: SpellcastingAbility?,
    val info: List<SpellInfo>?
)
