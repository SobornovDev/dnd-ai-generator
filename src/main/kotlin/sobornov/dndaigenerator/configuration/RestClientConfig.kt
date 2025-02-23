package sobornov.dndaigenerator.configuration

import org.springframework.boot.convert.DurationStyle
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import sobornov.dndaigenerator.configuration.properties.RestClientProperties

@Configuration
class RestClientConfig(
    private val properties: RestClientProperties
) {

    @Bean
    fun restTemplate(builder: RestTemplateBuilder) =
        builder
            .rootUri(properties.url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .connectTimeout(DurationStyle.detectAndParse(properties.timeout))
            .build()
}
