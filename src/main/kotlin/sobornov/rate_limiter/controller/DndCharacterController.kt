package sobornov.rate_limiter.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sobornov.rate_limiter.model.CharacterRequest

@RestController
@RequestMapping("/api")
class DndCharacterController {

    private val log: Logger = LoggerFactory.getLogger(DndCharacterController::class.java)

    @PostMapping("/generate-character")
    fun generateCharacter(@RequestBody request: CharacterRequest): ResponseEntity<*> =
        handle(request.id) {}

    private fun <T> handle(
        requestId: String,
        getResponse: () -> T
    ): ResponseEntity<*> = try {
        log.info("Received event with id: $requestId")
        val response = getResponse()
        ResponseEntity(response, HttpStatus.OK)
    } catch (ex: Exception) {
        ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}