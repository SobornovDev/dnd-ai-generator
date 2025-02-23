package sobornov.dndaigenerator.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("web")
class WebClientProperties {
    lateinit var timeout: String
    lateinit var url: String

    lateinit var openai: OpenAi
}

class OpenAi {
    lateinit var apiKey: String
}
