package sobornov.dndaigenerator.service

import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionMessage
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionMessage.Role
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import sobornov.dndaigenerator.client.OpenAiClient
import sobornov.dndaigenerator.model.request.CharacterRequest
import sobornov.dndaigenerator.model.response.OpenAiResponse

@Service
class OpenAiGenerator(
    private val webClient: OpenAiClient
) : Generator {
    override val model: String = "gpt-4o-mini"

    override fun generate(request: CharacterRequest): Mono<OpenAiResponse> {
        val chatCompletion = ChatCompletionRequest(
            listOf(
                ChatCompletionMessage("You are DnD character generator", Role.SYSTEM),
                ChatCompletionMessage(request.toString(), Role.USER)
            ),
            model,
            1.0
        )
        return webClient.call(request.id, chatCompletion)
    }
}
