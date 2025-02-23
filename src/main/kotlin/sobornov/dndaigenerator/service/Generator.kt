package sobornov.dndaigenerator.service

import reactor.core.publisher.Mono
import sobornov.dndaigenerator.model.request.CharacterRequest
import sobornov.dndaigenerator.model.response.CharacterResponse

interface Generator {
    val model: String
    suspend fun generate(request: CharacterRequest): Mono<CharacterResponse>
}
