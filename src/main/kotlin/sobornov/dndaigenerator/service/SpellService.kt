package sobornov.dndaigenerator.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import sobornov.dndaigenerator.client.DndapiClient
import sobornov.dndaigenerator.model.request.CharacterClass
import sobornov.dndaigenerator.model.response.context.CharacterContext

@Service
class SpellService(
    private val client: DndapiClient
) {
    suspend fun getSpells(className: CharacterClass, context: CharacterContext) {
        val response = client.getSpellsByClass(className.getValue())
            ?: throw RestClientException("Error during get spell list, id ${context.requestId}")
        context.spellData.complete(response)
    }

    suspend fun getSpellBook(level: Int = 1, school: String = "evocation", context: CharacterContext) {
        val response = client.getSpellBook(level, school)
            ?: throw RestClientException("Error during get spell book, id ${context.requestId}")
        context.spellBookData.complete(response)
    }
}
