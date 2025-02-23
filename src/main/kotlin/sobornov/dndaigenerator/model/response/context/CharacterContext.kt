package sobornov.dndaigenerator.model.response.context

import kotlinx.coroutines.CompletableDeferred
import sobornov.dndaigenerator.model.response.dndapi.SpellList
import sobornov.dndaigenerator.model.response.dndapi.SpellcastingInfo
import sobornov.dndaigenerator.model.response.openai.OpenAiResponse

data class CharacterContext(
    val requestId: String,
    var openAiData: CompletableDeferred<OpenAiResponse> = CompletableDeferred(),
    var spellData: CompletableDeferred<SpellcastingInfo> = CompletableDeferred(),
    var spellBookData: CompletableDeferred<SpellList> = CompletableDeferred()
) {
    suspend fun getOpenAiData() = openAiData.await()
    suspend fun getSpellData() = spellData.await()
    suspend fun getSpellBookData() = spellBookData.await()
}
