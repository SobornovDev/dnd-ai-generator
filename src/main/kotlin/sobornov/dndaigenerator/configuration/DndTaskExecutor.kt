package sobornov.dndaigenerator.configuration

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import sobornov.dndaigenerator.configuration.properties.ThreadPoolExecutorProperties

class DndTaskExecutor(
    private val properties: ThreadPoolExecutorProperties
) : ThreadPoolTaskExecutor() {
    init {
        corePoolSize = properties.corePoolSize
        maxPoolSize = properties.maxPoolSize
        queueCapacity = properties.queueCapacity
        initialize()
    }
}
