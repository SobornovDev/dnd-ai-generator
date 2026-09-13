# D&D AI Character Generator

Kotlin + Spring WebFlux service that builds a Dungeons & Dragons character from a short request: race, class, alignment, then enriches it with official D&D 5e data and an LLM write-up.

Portfolio project — not a product. It shows how I structure a reactive backend: typed request models, dedicated HTTP clients, thread-pool isolation for blocking calls, and a thin REST controller.

## Stack

- Kotlin, Spring Boot, WebFlux
- WebClient + RestClient for outbound APIs
- OpenAI client for narrative generation
- [D&D 5e API](https://www.dnd5eapi.co/) for spells and class data
- Detekt

## How it works

1. `POST /api/generate-character` accepts race / class / alignment.
2. `SpellService` pulls class spellcasting data from the D&D API.
3. `OpenAiGenerator` asks the model for a character sheet in a fixed JSON shape.
4. Response is returned as `CharacterResponse` (attributes, story, spells).

## Run locally

```bash
export OPENAI_API_KEY=sk-...
./gradlew bootRun
```

```bash
curl -X POST http://localhost:8080/api/generate-character \
  -H 'Content-Type: application/json' \
  -d '{
    "id": "demo-1",
    "specie": "ELF",
    "characterClass": "WIZARD",
    "alignment": "LAWFUL_GOOD"
  }'
```

Set the OpenAI key and base URLs in `src/main/resources/application.yml` (do not commit secrets).

## Layout

```
src/main/kotlin/sobornov/dndaigenerator
├── controller     REST entry point
├── service        generation + spells
├── client         D&D API and OpenAI
├── configuration  WebClient / RestClient / executors
└── model          request and response types
```

## What I would add next

- Testcontainers tests around the controller
- Idempotency on `id`
- Schema validation for the model output

MIT © Vladimir Sobornov
