package sobornov.rate_limiter.model

import jakarta.validation.Valid
import java.util.*

data class CharacterRequest(
    val id: String = UUID.randomUUID().toString(),
    val name: String?,
    val specie: Specie?,
    val `class`: CharacterClass?,
    @Valid
    val attributes: Attributes?,
    val alignment: Alignment?
)

enum class CharacterClass(value: String) {
    FIGHTER("fighter"),
    BARBARIAN("barbarian"),
    BARD("bard"),
    CLERIC("cleric"),
    DRUID("druid"),
    MONK("monk"),
    PALADIN("paladin"),
    RANGER("ranger"),
    ROGUE("rogue"),
    SORCERER("sorcerer"),
    WARLOCK("warlock"),
    WIZARD("wizard"),
    ARTIFICER("artificer")
}

enum class Specie(value: String) {
    DWARF("dwarf"),
    ELF("elf"),
    HALFLING("halfling"),
    HUMAN("human"),
    DRAGONBORN("dragonborn"),
    ORC("orc"),
    TIEFLING("tiefling"),
    GOBLIN("goblin"),
    KENKU("kenku"),
    TABAXI("tabaxi")
}

enum class Alignment(value: String) {
    LAWFULGOOD("Lawful Good"),
    NEUTRALGOOD("Neutral Good"),
    CHAOTICGOOD("Chaotic Good"),
    LAWFULNEUTRAL("Lawful Neutral"),
    TRUENEUTRAL("True Neutral"),
    CHAOTICNEUTRAL("Chaotic Neutral"),
    LAWFULEVIL("Lawful Evil"),
    NEUTRALEVIL("Neutral Evil"),
    CHAOTICEVIL("Chaotic Evil"),
}