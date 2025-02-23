package sobornov.dndaigenerator.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import sobornov.dndaigenerator.model.response.ErrorResponse

@Component
class WebUtils {

    private val log: Logger = LoggerFactory.getLogger(WebUtils::class.java)

    suspend fun <T> fetch(
        requestId: String,
        getResponse: suspend () -> Mono<T>
    ): Mono<ResponseEntity<*>> {
        log.info("Received event with id: $requestId")
        return getResponse().map { response ->
            ResponseEntity(response, HttpStatus.OK) as ResponseEntity<*>
        }.onErrorResume { ex ->
            log.error("Error during event with id: $requestId, message: ${ex.message}")
            Mono.just(
                ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                        ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            ex.message ?: "Empty message"
                        )
                    )
            )
        }
    }
}
