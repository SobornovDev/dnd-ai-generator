package sobornov.dndaigenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import sobornov.dndaigenerator.configuration.WebClientProperties

@SpringBootApplication
@EnableConfigurationProperties(
    WebClientProperties::class
)
class AiGeneratorApplication

fun main() {
    runApplication<AiGeneratorApplication>()
}
