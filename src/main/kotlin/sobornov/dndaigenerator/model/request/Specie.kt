package sobornov.dndaigenerator.model.request

import com.fasterxml.jackson.annotation.JsonValue

enum class Specie(private val value: String) {
    DWARF("dwarf"),
    ELF("elf"),
    HALFLING("halfling"),
    HUMAN("human"),
    DRAGONBORN("dragonborn"),
    ORC("orc"),
    TIEFLING("tiefling"),
    GOBLIN("goblin"),
    KENKU("kenku"),
    TABAXI("tabaxi");

    @JsonValue
    fun getValue(): String = value
}
