package sobornov.dndaigenerator.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("threads")
@Suppress("MagicNumber")
class ThreadPoolExecutorProperties {
    var corePoolSize: Int = 4
    var maxPoolSize: Int = 8
    var queueCapacity: Int = 10
}
