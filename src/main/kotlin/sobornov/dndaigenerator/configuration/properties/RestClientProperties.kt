package sobornov.dndaigenerator.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("rest")
class RestClientProperties {
    lateinit var timeout: String
    lateinit var url: String
    lateinit var spells: String
}
