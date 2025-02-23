package sobornov.dndaigenerator.service

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionMessage
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionMessage.Role
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import sobornov.dndaigenerator.client.OpenAiClient
import sobornov.dndaigenerator.extension.awaitAll
import sobornov.dndaigenerator.extension.getBackstory
import sobornov.dndaigenerator.model.request.CharacterRequest
import sobornov.dndaigenerator.model.response.CharacterResponse
import sobornov.dndaigenerator.model.response.context.CharacterContext

@Service
class OpenAiGenerator(
    private val webClient: OpenAiClient,
    private val spellService: SpellService,
    private val dispatcher: CoroutineDispatcher,
) : Generator {
    override val model: String = "gpt-4o-mini"

    override suspend fun generate(request: CharacterRequest): Mono<CharacterResponse> {
        val context = CharacterContext(request.id)
        val chatCompletion = ChatCompletionRequest(
            listOf(
                ChatCompletionMessage("You are DnD character generator", Role.SYSTEM),
                ChatCompletionMessage("Generate backstory $request", Role.USER),
                ChatCompletionMessage("Generate character quote $request", Role.USER),
            ),
            model,
            1.0
        )

        coroutineScope {
            withContext(dispatcher) {
                for (i in 1..request.level) {
                    launch { spellService.getSpellBook(i, context = context) }
                }
                launch { spellService.getSpells(request.`class`, context) }
            }
        }
        webClient.call(chatCompletion, context)

        context.awaitAll()
        return Mono.just(
            CharacterResponse(
                requestId = request.id,
                responseId = context.getOpenAiData().id,
                generatedBackstory = context.getOpenAiData().getBackstory(),
                combatAbilities = mapOf(),
                spells = context.getSpellData().info
                    ?.filter { !it.name.isNullOrBlank() }
                    ?.associate { it.name!! to it.desc?.firstOrNull() },
                spellBook = context.getSpellBookData().results
                    .associate { it.name to it.index },
                dndStats = request.attributes,
                aiGeneratedQuote = null,
                executionTime = null
            )
        )
    }
}
