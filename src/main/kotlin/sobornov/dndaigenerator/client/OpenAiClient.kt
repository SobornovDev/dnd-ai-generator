package sobornov.dndaigenerator.client

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest
import org.springframework.boot.convert.DurationStyle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import sobornov.dndaigenerator.configuration.WebClientProperties
import sobornov.dndaigenerator.exception.GenerationException
import sobornov.dndaigenerator.model.response.OpenAiResponse

@Service
class OpenAiClient(
    private val webClient: WebClient,
    private val webClientProperties: WebClientProperties
) {
    private val log: Logger = LoggerFactory.getLogger(OpenAiClient::class.java)

    fun call(requestId: String, request: ChatCompletionRequest): Mono<OpenAiResponse> {
        return webClient.post()
            .bodyValue(request)
            .retrieve()
            .bodyToMono(OpenAiResponse::class.java)
            .timeout(DurationStyle.detectAndParse(webClientProperties.timeout))
            .doOnError { error ->
                log.error("Error during generation: $requestId, error: ${error.message}")
                throw GenerationException(error.message)
            }
    }
}
