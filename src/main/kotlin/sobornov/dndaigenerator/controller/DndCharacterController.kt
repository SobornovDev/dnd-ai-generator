package sobornov.dndaigenerator.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import sobornov.dndaigenerator.model.request.CharacterRequest
import sobornov.dndaigenerator.service.Generator
import sobornov.dndaigenerator.utils.WebUtils

@RestController
@RequestMapping("/api")
class DndCharacterController(
    private val utils: WebUtils,
    private val generator: Generator
) {

    @PostMapping("/generate-character")
    fun generateCharacter(@RequestBody request: CharacterRequest): Mono<ResponseEntity<*>> =
        utils.handle(request.id) { generator.generate(request) }
}
