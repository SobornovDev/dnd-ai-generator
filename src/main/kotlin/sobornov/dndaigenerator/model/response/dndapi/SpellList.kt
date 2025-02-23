package sobornov.dndaigenerator.model.response.dndapi

data class SpellList(
    val count: Int,
    val results: List<Spell>
)

data class Spell(
    val index: String,
    val name: String,
    val level: Int,
    val url: String?
)
