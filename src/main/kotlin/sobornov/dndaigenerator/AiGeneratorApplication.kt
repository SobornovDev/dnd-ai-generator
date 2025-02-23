package sobornov.dndaigenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import sobornov.dndaigenerator.configuration.properties.RestClientProperties
import sobornov.dndaigenerator.configuration.properties.ThreadPoolExecutorProperties
import sobornov.dndaigenerator.configuration.properties.WebClientProperties

@SpringBootApplication
@EnableConfigurationProperties(
    WebClientProperties::class,
    ThreadPoolExecutorProperties::class,
    RestClientProperties::class
)
class AiGeneratorApplication

fun main() {
    runApplication<AiGeneratorApplication>()
}
