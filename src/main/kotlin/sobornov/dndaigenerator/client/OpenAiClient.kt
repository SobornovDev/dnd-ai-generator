package sobornov.dndaigenerator.client

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest
import org.springframework.boot.convert.DurationStyle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import sobornov.dndaigenerator.configuration.properties.WebClientProperties
import sobornov.dndaigenerator.exception.GenerationException
import sobornov.dndaigenerator.model.response.context.CharacterContext
import sobornov.dndaigenerator.model.response.openai.OpenAiResponse

@Service
class OpenAiClient(
    private val webClient: WebClient,
    private val webClientProperties: WebClientProperties
) {
    private val log: Logger = LoggerFactory.getLogger(OpenAiClient::class.java)

    fun call(request: ChatCompletionRequest, context: CharacterContext) =
        webClient.post()
            .bodyValue(request)
            .retrieve()
            .bodyToMono(OpenAiResponse::class.java)
            .timeout(DurationStyle.detectAndParse(webClientProperties.timeout))
            .doOnError { error ->
                log.error("Error during generation: $${context.requestId}, error: ${error.message}")
                throw GenerationException(error.message)
            }.subscribe { response ->
                log.info("Received aiResponse: ${response.id}")
                context.openAiData.complete(response)
            }
}
