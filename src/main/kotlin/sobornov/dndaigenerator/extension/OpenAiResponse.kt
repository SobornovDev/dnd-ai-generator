package sobornov.dndaigenerator.extension

import sobornov.dndaigenerator.exception.GenerationException
import sobornov.dndaigenerator.model.openai.OpenAiResponse

private const val ASSISTANT = "assistant"
fun OpenAiResponse.getBackstory() =
    this.choices
        ?.firstOrNull { it.message?.role == ASSISTANT }
        ?.message
        ?.content ?: throw GenerationException("Cannot find message with $ASSISTANT role")
