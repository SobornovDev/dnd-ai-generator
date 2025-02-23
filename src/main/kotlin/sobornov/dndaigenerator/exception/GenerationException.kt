package sobornov.dndaigenerator.exception

class GenerationException(
    message: String?,
    cause: Throwable? = null
) : RuntimeException(message, cause)
