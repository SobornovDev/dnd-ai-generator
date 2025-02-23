package sobornov.dndaigenerator.client

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import sobornov.dndaigenerator.configuration.properties.RestClientProperties
import sobornov.dndaigenerator.model.response.dndapi.SpellList
import sobornov.dndaigenerator.model.response.dndapi.SpellcastingInfo
import sobornov.dndaigenerator.utils.getForObject

@Service
class DndapiClient(
    private val restTemplate: RestTemplate,
    private val properties: RestClientProperties
) {
    private val log: Logger = LoggerFactory.getLogger(DndapiClient::class.java)
    fun getSpellsByClass(index: String): SpellcastingInfo? {
        val response = restTemplate
            .getForObject<SpellcastingInfo>(properties.url + properties.spells, index)
        log.info("Received spells: $response")
        return response
    }

    fun getSpellBook(level: Int, school: String): SpellList? {
        val response = restTemplate
            .getForObject<SpellList>(properties.url + properties.spellBook, level, school)
        log.info("Received spell book: $response")
        return response
    }
}
