package sobornov.dndaigenerator.service

import sobornov.dndaigenerator.model.request.CharacterRequest
import sobornov.dndaigenerator.model.response.CharacterResponse

interface Generator {
    fun generate(request: CharacterRequest): CharacterResponse
}
