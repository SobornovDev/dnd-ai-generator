package sobornov.dndaigenerator.extension

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import sobornov.dndaigenerator.model.response.context.CharacterContext

suspend fun CharacterContext.awaitAll() = coroutineScope {
    listOf(
        async {
            openAiData.await()
            spellData.await()
            spellBookData.await()
        }
    ).awaitAll()
}
