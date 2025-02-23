package sobornov.dndaigenerator.configuration

import kotlinx.coroutines.asCoroutineDispatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import reactor.core.scheduler.Schedulers
import sobornov.dndaigenerator.configuration.properties.ThreadPoolExecutorProperties

@Configuration
class ThreadPoolExecutorConfig(
    private val properties: ThreadPoolExecutorProperties
) {
    @Bean
    fun taskExecutor(): TaskExecutor = DndTaskExecutor(properties)

    @Bean
    fun taskScheduler(taskExecutor: TaskExecutor) =
        Schedulers.fromExecutor(taskExecutor)

    @Bean
    fun coroutineDispatcher(taskExecutor: TaskExecutor) =
        taskExecutor.asCoroutineDispatcher()
}
