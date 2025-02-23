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
        val spellsResponse = spellService.getSpells(request.`class`)
        val aiResponse = webClient.call(request.id, chatCompletion)

        return Mono.zip(aiResponse, spellsResponse).map { tuple ->
            val aiStory = tuple.t1
            val spells = tuple.t2
            CharacterResponse(
                requestId = request.id,
                responseId = aiStory.id,
                generatedBackstory = aiStory.getBackstory(),
                combatAbilities = mapOf(),
                spells = spells.info
                    ?.filter { !it.name.isNullOrBlank() }
                    ?.associate { it.name!! to it.desc?.firstOrNull() },
                dndStats = request.attributes,
                aiGeneratedQuote = null,
                executionTime = null
            )
        }
    }
}
