package sobornov.dndaigenerator.model.request

import com.fasterxml.jackson.annotation.JsonValue

enum class Alignment(private val value: String) {
    LAWFULGOOD("Lawful Good"),
    NEUTRALGOOD("Neutral Good"),
    CHAOTICGOOD("Chaotic Good"),
    LAWFULNEUTRAL("Lawful Neutral"),
    TRUENEUTRAL("True Neutral"),
    CHAOTICNEUTRAL("Chaotic Neutral"),
    LAWFULEVIL("Lawful Evil"),
    NEUTRALEVIL("Neutral Evil"),
    CHAOTICEVIL("Chaotic Evil");

    @JsonValue
    fun getValue(): String = value
}
