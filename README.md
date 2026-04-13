# ApothSB

A lightweight Forge compatibility mod that fixes Apotheosis gems being rejected by Sophisticated Backpacks tag filter slots.

## Features

* Allows Apotheosis gems to be placed into Sophisticated Backpacks tag filter slots.
* Enables tag-based filtering (Pickup, Magnet, Filter, and similar upgrades) to recognize gems as a filterable tag.
* Adds the `apotheosis:gem` item to the `apotheosis:gem` item tag via a datapack tag entry, with a runtime fallback that injects the tag through `TagsUpdatedEvent`.
* Soft dependency on both target mods: does nothing unless Apotheosis and Sophisticated Backpacks are both present.
* No mixins, no Mixin runtime conflicts, no access transformers.

## Requirements

* [Apotheosis](https://www.curseforge.com/minecraft/mc-mods/apotheosis) (required)
* [Sophisticated Backpacks](https://www.curseforge.com/minecraft/mc-mods/sophisticated-backpacks) (required)
* Sophisticated Core (required, installed automatically with Sophisticated Backpacks)

## Installation

1. Install Minecraft Forge for 1.20.1.
2. Place `apothsb-1.0.0.jar` into your `mods/` folder alongside Apotheosis and Sophisticated Backpacks.
3. Launch the game. The mod activates automatically when both target mods are detected.

## Compatibility

* Minecraft 1.20.1
* Minecraft Forge 47.x
* Apotheosis 7.x
* Sophisticated Backpacks 3.x

## Building from Source

```
git clone https://github.com/Nightwielder23/apothsb
cd apothsb
./gradlew build
```

The output jar is placed in `build/libs/`.

## License

Released under the MIT License. See [LICENSE](LICENSE) for the full text.
