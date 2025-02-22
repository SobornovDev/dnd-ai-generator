package sobornov.dndaigenerator.configuration

import io.ktor.http.headersOf
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient =
        builder
            .defaultHeader(headersOf("Content-Type" to listOf("application/json")).toString())
            .build()
}
