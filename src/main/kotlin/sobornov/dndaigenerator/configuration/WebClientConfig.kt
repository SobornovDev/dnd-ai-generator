package sobornov.dndaigenerator.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    private val webClientProperties: WebClientProperties
) {

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient =
        builder
            .defaultHeaders {
                it.setBearerAuth(webClientProperties.openai.apiKey)
                it.contentType = MediaType.APPLICATION_JSON
            }
            .baseUrl(webClientProperties.url)
            .build()
}
