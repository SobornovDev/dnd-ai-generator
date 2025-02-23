package sobornov.dndaigenerator.model.request

import com.fasterxml.jackson.annotation.JsonValue

enum class CharacterClass(private val value: String) {
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
    ARTIFICER("artificer");

    @JsonValue
    fun getValue(): String = value
}
