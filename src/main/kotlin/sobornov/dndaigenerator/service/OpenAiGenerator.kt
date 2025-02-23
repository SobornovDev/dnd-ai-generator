package sobornov.dndaigenerator.service

import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionMessage
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionMessage.Role
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import sobornov.dndaigenerator.client.OpenAiClient
import sobornov.dndaigenerator.extension.getBackstory
import sobornov.dndaigenerator.model.request.CharacterRequest
import sobornov.dndaigenerator.model.response.CharacterResponse

@Service
class OpenAiGenerator(
    private val webClient: OpenAiClient,
    private val spellService: SpellService
) : Generator {
    override val model: String = "gpt-4o-mini"

    override fun generate(request: CharacterRequest): Mono<CharacterResponse> {
        val chatCompletion = ChatCompletionRequest(
            listOf(
                ChatCompletionMessage("You are DnD character generator", Role.SYSTEM),
                ChatCompletionMessage("Generate backstory $request", Role.USER),
                ChatCompletionMessage("Generate character quote $request", Role.USER),
            ),
            model,
            1.0
        )
        val spells = spellService.getSpells(request.`class`).block()
        println(spells)
        return webClient.call(request.id, chatCompletion).map { response ->
            CharacterResponse(
                requestId = request.id,
                responseId = response.id,
                generatedBackstory = response.getBackstory(),
                combatAbilities = mapOf(),
                spells = mapOf(),
                dndStats = request.attributes,
                aiGeneratedQuote = null,
                executionTime = null
            )
        }
    }
}
