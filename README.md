# ApothSB

Tiny Forge compatibility patch for 1.19.2. Apotheosis gems aren't in any item tag, so the filter, pickup, and magnet upgrade slots in Sophisticated Backpacks reject them. ApothSB drops the gem item into the `apotheosis:gem` tag so those slots will accept it.

It ships a small datapack tag entry plus a runtime fallback that re-injects the tag on `TagsUpdatedEvent` (no mixins, no AT). The mod no-ops unless both Apotheosis and Sophisticated Backpacks are installed.

## Requirements

- Minecraft 1.19.2, Forge 43.x
- [Apotheosis](https://www.curseforge.com/minecraft/mc-mods/apotheosis) — tested against 6.5.2
- [Sophisticated Backpacks](https://www.curseforge.com/minecraft/mc-mods/sophisticated-backpacks)

## Building

`./gradlew build`. Jar lands in `build/libs/`.

## License

MIT — see [LICENSE](LICENSE).
