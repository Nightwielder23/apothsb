# ApothSB

Tiny Forge compatibility patch for 1.19.2. Apotheosis gems aren't in any item tag, so the filter, pickup, and magnet upgrade slots in Sophisticated Backpacks reject them. ApothSB drops the gem item into the `apotheosis:gem` tag so those slots will accept it.

The fix is a small datapack tag entry, with no mixins and no access transformer. The patch does nothing unless both Apotheosis and Sophisticated Backpacks are installed.

## Requirements

- Minecraft 1.19.2, Forge 43.x
- [Apotheosis](https://www.curseforge.com/minecraft/mc-mods/apotheosis), tested against 6.5.2
- [Sophisticated Backpacks](https://www.curseforge.com/minecraft/mc-mods/sophisticated-backpacks)

## License

MIT, see [LICENSE](LICENSE).
