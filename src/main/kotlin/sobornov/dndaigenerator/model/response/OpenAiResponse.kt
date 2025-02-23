package sobornov.dndaigenerator.model.response

data class OpenAiResponse(
    val id: String,
    val choices: List<Choice>?
)

data class Choice(
    val index: Int,
    val message: ChatMessage?,
    val finishReason: String?
)

data class ChatMessage(
    val role: String,
    val content: String
)
