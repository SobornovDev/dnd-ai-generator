package sobornov.dndaigenerator.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("web")
class WebClientProperties {
    val timeout: Long? = null
}
