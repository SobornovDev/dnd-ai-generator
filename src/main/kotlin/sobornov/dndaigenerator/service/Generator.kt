package sobornov.dndaigenerator.service

import reactor.core.publisher.Mono
import sobornov.dndaigenerator.model.request.CharacterRequest
import sobornov.dndaigenerator.model.response.OpenAiResponse

interface Generator {
    val model: String
    fun generate(request: CharacterRequest): Mono<OpenAiResponse>
}
