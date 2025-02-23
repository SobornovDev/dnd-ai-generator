package sobornov.dndaigenerator.client

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import sobornov.dndaigenerator.configuration.properties.RestClientProperties
import sobornov.dndaigenerator.model.dndapi.SpellcastingInfo
import sobornov.dndaigenerator.utils.getForObject

@Service
class DndapiClient(
    private val restTemplate: RestTemplate,
    private val properties: RestClientProperties
) {
    fun getSpellsByClass(index: String): SpellcastingInfo? {
        return restTemplate
            .getForObject(properties.url + properties.spells, index)
    }
}
