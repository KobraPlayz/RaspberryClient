# Raspberry Client

A professional, high-performance Minecraft 1.8.9 PVP client optimized for competitive gameplay.

## Features

### HUD Modules
- **FPS Display** - Shows current frames per second with color coding
- **CPS Counter** - Tracks clicks per second for both mouse buttons
- **Keystrokes** - Visual display of WASD keys and mouse buttons
- **Coordinates** - Displays current X, Y, Z coordinates

### Visual Enhancement Modules
- **Fullbright** - Maximum brightness for better visibility
- **Zoom** - Configurable zoom functionality (default key: C)

### Movement Modules
- **Toggle Sprint** - Automatically sprint without holding the key
- **Toggle Sneak** - Toggle crouch instead of holding

### Interface
- **ClickGUI** - Category-based module management interface (default key: Right Shift)
- Draggable panels with color-coded categories
- Easy module toggling and configuration

## Technology Stack
- **Minecraft Version**: 1.8.9
- **Forge Version**: 1.8.9-11.15.1.2318
- **Build System**: ForgeGradle 2.1
- **Mixin Framework**: SpongePowered Mixin 0.7.11
- **Java Version**: 8

## Installation

1. Install Minecraft 1.8.9
2. Install Minecraft Forge 1.8.9-11.15.1.2318
3. Place the Raspberry-Client JAR file in your `.minecraft/mods/` folder
4. Launch Minecraft with the Forge profile

## Building from Source

```bash
# Setup workspace
./gradlew setupDecompWorkspace

# Build the mod
./gradlew build
```

The compiled JAR will be located in `build/libs/`

## Usage

### Opening the ClickGUI
- Press **Right Shift** to open the ClickGUI
- Left-click to toggle modules
- Right-click on category headers to collapse/expand
- Drag panels to reposition them

### Using Modules
- **Zoom**: Hold **C** to zoom in
- **Toggle Sprint**: Enabled by default, automatically sprints when moving forward
- **Fullbright**: Toggle for maximum brightness
- All other modules can be toggled through the ClickGUI

### Configuration
- Settings are automatically saved to `.minecraft/raspberry/config.json`
- Module states, keybinds, and settings are preserved between sessions

## Architecture

### Core Systems
- **Module System**: Flexible module architecture with category-based organization
- **Event System**: Priority-based event bus using Mixin injections
- **Settings System**: Support for boolean, numeric, and mode-based settings
- **Configuration System**: JSON-based config save/load

### Code Structure
```
src/main/java/com/raspberry/client/
├── Raspberry.java              # Main mod class
├── modules/                    # Module system
│   ├── Module.java            # Base module class
│   ├── ModuleManager.java     # Module registry
│   ├── HudModule.java         # Base HUD module
│   ├── combat/                # Combat modules
│   ├── visual/                # Visual enhancement modules
│   ├── movement/              # Movement modules
│   ├── render/                # HUD render modules
│   └── misc/                  # Miscellaneous modules
├── events/                     # Event system
│   ├── Event.java             # Base event class
│   ├── EventManager.java      # Event bus
│   └── types/                 # Event type definitions
├── settings/                   # Settings system
│   ├── Setting.java           # Base setting class
│   └── types/                 # Setting type implementations
├── gui/                        # GUI system
│   ├── clickgui/              # ClickGUI implementation
│   └── hud/                   # HUD editor (future)
├── mixins/                     # Mixin injections
│   ├── MixinMinecraft.java
│   ├── MixinGuiIngame.java
│   └── ...
├── config/                     # Configuration system
│   └── ConfigManager.java     # Config save/load
└── utils/                      # Utility classes
    ├── RenderUtils.java       # Rendering helpers
    └── ColorUtils.java        # Color manipulation
```

## Performance

Raspberry Client is designed with performance in mind:
- Minimal overhead on game performance
- Optimized rendering code
- Efficient event handling
- Memory-conscious module architecture

## Fair Play

This client focuses on quality-of-life improvements and fair competitive advantages:
- ✅ FPS optimization
- ✅ Enhanced HUD and visuals
- ✅ Toggle sprint/sneak
- ✅ Zoom functionality
- ❌ NO unfair advantages (killaura, speed, fly, x-ray, etc.)

All features are within the rules of major Minecraft servers.

## License

See LICENSE file for details.

## Credits

Created for competitive PVP gameplay on Minecraft 1.8.9.
Built with ForgeGradle and SpongePowered Mixin.

---

**Version**: 1.0.0
**Minecraft**: 1.8.9
**Forge**: 1.8.9-11.15.1.2318
