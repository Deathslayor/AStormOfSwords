# Project Context for Local Qwen

This repository is the Minecraft mod **A Storm of Sorts** (`agotmod`), a Game of Thrones themed NeoForge mod for Minecraft 1.21.1.

Use this file as the first project memory before doing programming work. Prefer repository evidence over assumptions.

## Local AI Role

Qwen3-14B is the programming assistant for this project.

Codex should be used mainly for non-programming writing tasks such as project summaries, documentation wording, prompts, issue text, PR descriptions, and commit message wording.

When programming, Qwen should:

- Read the relevant files before proposing edits.
- Prefer the existing NeoForge, DeferredRegister, packet, datagen, and resource patterns already present in the repo.
- Make small, scoped changes.
- Avoid broad refactors unless the user explicitly asks.
- Avoid overwriting unrelated uncommitted work.
- Explain risks before touching shared systems such as registries, networking, NPC AI, mixins, worldgen, or generated resources.
- Verify changes with Gradle when possible.

## Project Basics

- Mod id: `agotmod`
- Mod name: `A Storm of Sorts`
- Java package root: `net.darkflameproduction.agotmod`
- Main mod class: `src/main/java/net/darkflameproduction/agotmod/AGoTMod.java`
- Minecraft version: `1.21.1`
- NeoForge version: `21.1.227`
- Java toolchain in Gradle: Java 21
- Build files: `build.gradle`, `gradle.properties`, `settings.gradle.kts`

Important dependency notes:

- NeoForge userdev is used through `net.neoforged.gradle.userdev`.
- GeckoLib is jarJar bundled.
- TerraBlender is jarJar bundled.
- JEI is compileOnly/runtimeOnly.
- Epic Fight is compileOnly/localRuntime.
- `libs/ctgen-neoforge-2.1.jar` is a local jarJar dependency for CTGen.

## Build and Run Commands

Use these from the repository root:

```powershell
.\gradlew.bat build
.\gradlew.bat check
.\gradlew.bat runClient
.\gradlew.bat runServer
.\gradlew.bat runData
```

The Gradle `data` run writes generated resources to `src/generated/resources` and uses `src/main/resources` as existing resources.

## Current CI Caveat

`.github/workflows/gradle-main.yml` currently sets up JDK 17, while `build.gradle` requests Java 21. Treat this as a likely CI/local mismatch until verified.

## Repository Layout

Primary handwritten Java lives under:

- `src/main/java/net/darkflameproduction/agotmod/armor` - custom armor items, GeckoLib armor models, renderers.
- `src/main/java/net/darkflameproduction/agotmod/block` - block registration and custom block classes, including crops, barrels, furniture, signs, town hall, job barrels, special leaves.
- `src/main/java/net/darkflameproduction/agotmod/client` - client-only data, renderers, overlays, key input, GUI helpers.
- `src/main/java/net/darkflameproduction/agotmod/command` - commands such as house banner setup.
- `src/main/java/net/darkflameproduction/agotmod/data` - saved data and lore house data.
- `src/main/java/net/darkflameproduction/agotmod/datagen` - data generators for block states, item models, tags, loot, recipes, worldgen.
- `src/main/java/net/darkflameproduction/agotmod/entity` - entities, block entities, NPC AI, NPC systems, renderers, models, entity goals.
- `src/main/java/net/darkflameproduction/agotmod/event` - NeoForge event handlers.
- `src/main/java/net/darkflameproduction/agotmod/gui` - screens and GUI data.
- `src/main/java/net/darkflameproduction/agotmod/init` - menu type registration.
- `src/main/java/net/darkflameproduction/agotmod/inventory` - NPC inventory menu and screen.
- `src/main/java/net/darkflameproduction/agotmod/item` - item registration, tool tiers, foods, consumables, pricing-related item behavior.
- `src/main/java/net/darkflameproduction/agotmod/magic` - mana/client magic data.
- `src/main/java/net/darkflameproduction/agotmod/mixin` - mixins configured by `src/main/resources/asos.mixins.json`.
- `src/main/java/net/darkflameproduction/agotmod/network` - custom packets and packet handlers.
- `src/main/java/net/darkflameproduction/agotmod/particle` - particle registration.
- `src/main/java/net/darkflameproduction/agotmod/sound` - sound registration.
- `src/main/java/net/darkflameproduction/agotmod/util` - tags, wood types, attributes, pricing, color and temperature helpers.
- `src/main/java/net/darkflameproduction/agotmod/villager` - villager registration and trades.
- `src/main/java/net/darkflameproduction/agotmod/worldgen` - biome, feature, tree, ore, TerraBlender, and configured/placed feature logic.

Primary resources live under:

- `src/main/resources/assets/agotmod` - models, textures, blockstates, lang, sounds, animations.
- `src/main/resources/data/agotmod` - data packs, worldgen, dimensions, map data, CTGen data, structures, roads, rivers, walls, waypoints.
- `src/main/resources/asos.mixins.json` - mixin configuration.
- `src/main/resources/pack.mcmeta` - resource pack metadata.
- `src/generated/resources` - generated resource output from datagen; do not hand-edit unless the user intentionally wants generated output modified.

## Entry Points and Shared Systems

Start with these files when working on core behavior:

- `AGoTMod.java` registers creative tabs, block entities, sounds, particles, villagers, blocks, items, menus, banner patterns, configured features, entities, attachments, event handlers, commands, client setup, and common setup.
- `block/ModBLocks.java` is the main block registry. It contains large static registration sections and maps for block variant families.
- `item/ModItems.java` is the main item registry. It contains many armor and weapon/item registrations.
- `entity/ModEntities.java` registers custom entities.
- `entity/ModBlockEntities.java` registers block entities.
- `network/ModNetworking.java` registers custom clientbound and serverbound payloads.
- `event/ModEvents.java`, `event/ModEventBusEvents.java`, and `event/ModEventBusClientEvents.java` contain event-bus logic.
- `datagen/DataGenerators.java` wires datagen providers.
- `worldgen/biome/ModTerrablender.java` and nearby worldgen classes handle biome/world generation integration.

## Main Feature Areas

Major systems visible in the codebase:

- Many custom blocks, building variants, crops, furniture, barrels, job blocks, signs, and special vegetation.
- Custom items, foods, consumables, coins, weapons, tools, armor materials, and faction armor sets.
- GeckoLib armor/entity/block animation and rendering.
- Custom entities including peasants/NPCs, direwolves, mammoths, crows, and seat entities.
- NPC job systems for farming, lumberjacking, mining, smelting, tailoring, tanning, trading, butchering, animal herding, guarding, hunger, home/sleep, culture, inventory, and town behavior.
- Town hall, town ownership, town protection/culture zones, and town notifications.
- House data, house names, banners, invites, roles, and related network sync.
- Currency/coin balance and trading/grocer interactions.
- Custom GUI screens and overlays.
- Mixin changes for player behavior, attack speed, campfires, server properties, world presets, create-world screen, and player renderer.
- Map-based Known World dimension/worldgen data, including biome and heightmap images.
- CTGen structure/rivers/roads/walls/waypoint data.

## Networking Pattern

Packets are registered in `network/ModNetworking.java` with a `PayloadRegistrar`.

When adding or modifying a packet:

- Find an existing similar packet and copy its style.
- Register the packet in `ModNetworking.register`.
- Keep client-only handling in client handlers or enqueue work on the client side.
- Keep server-side validation in server handlers.
- Be careful with house/town/NPC packets because they update persistent or shared gameplay state.

## Registry Pattern

The project uses NeoForge DeferredRegister patterns:

- Blocks: `ModBLocks.BLOCKS`
- Items: `ModItems.ITEMS`
- Entities: `ModEntities`
- Block entities: `ModBlockEntities`
- Menus: `ModMenuTypes`
- Particles: `ModParticles`
- Sounds: `ModSounds`
- Creative tabs: `item/creativetabs/*`

When adding a block or item, check all required surfaces:

- Java registry entry.
- Block item registration if needed.
- Creative tab entry.
- Model, blockstate, item model, texture, loot table, recipe, tag, and lang entries.
- Datagen provider updates if the project generates those resources.

## Resource and Datagen Rules

Prefer updating datagen providers over hand-editing generated output.

Handwritten resources in `src/main/resources` are source assets/data. Generated resources under `src/generated/resources` may be overwritten by `runData`.

For block/item additions, check:

- `datagen/ModBlockStateProvider.java`
- `datagen/ModItemModelProvider.java`
- `datagen/ModBlockTagGenerator.java`
- `datagen/ModItemTagGenerator.java`
- `datagen/ModLootTableProvider.java`
- `datagen/ModRecipeProvider.java`
- `datagen/loot/ModBlockLootTables.java`
- `datagen/loot/ModEntityLootTables.java`

## Mixin Rules

Mixins are configured in `src/main/resources/asos.mixins.json`.

When modifying mixins:

- Check whether the mixin is common or client-only.
- Keep package names aligned with `net.darkflameproduction.agotmod.mixin`.
- Avoid broad injections when a NeoForge event hook can do the job.
- Verify against Minecraft/NeoForge 1.21.1 mappings.

## Local Model Usage

Local endpoint:

- Base URL: `http://localhost:1234/v1`
- Chat model: `qwen/qwen3-14b`
- Embedding model available: `text-embedding-nomic-embed-text-v1.5`

Suggested system prompt for Qwen:

```text
You are the programming assistant for the Minecraft NeoForge mod "A Storm of Sorts".
Read PROJECT_CONTEXT.md first, then inspect relevant source files before proposing edits.
Use existing project patterns. Keep changes small and scoped. Do not overwrite unrelated uncommitted work.
Prefer datagen providers over hand-editing generated resources. Explain risks before touching registries, packets, mixins, NPC systems, or worldgen.
When possible, verify with Gradle commands and report exact failures.
```

## Things To Watch

- The codebase has large registry files with repeated patterns; search locally before inventing a new pattern.
- Generated resources can be huge and noisy. Avoid feeding all generated JSON to the model unless specifically relevant.
- Client/server separation matters. Do not reference client-only Minecraft classes from common/server code.
- Packet handlers must enqueue work where appropriate.
- NPC systems are interconnected; changes to goals, ticket systems, inventory, hunger, sleep, town, or job logic can have side effects.
- Worldgen and CTGen data are data-heavy; validate JSON shape and namespace paths carefully.
- Existing uncommitted changes may be present. Check `git status --short` before making edits.

