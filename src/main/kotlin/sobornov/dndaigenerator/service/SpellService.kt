package sobornov.dndaigenerator.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import sobornov.dndaigenerator.client.DndapiClient
import sobornov.dndaigenerator.model.request.CharacterClass

@Service
class SpellService(
    private val client: DndapiClient,
    private val scheduler: Scheduler,
) {
    fun getSpells(className: CharacterClass) = Mono.fromCallable {
        client.getSpellsByClass(className.getValue())
    }.subscribeOn(scheduler)
}
