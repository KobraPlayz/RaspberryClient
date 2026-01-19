# Raspberry Client - Release Files

## 📦 Available Files

### Raspberry-Client-1.0.0-sources.jar
**Source Code Archive** - Contains all Java source files and resources.

- **Size:** ~35 KB
- **Contents:**
  - All 41 Java source files
  - Resource files (mcmod.info, mixins.raspberry.json, raspberry_at.cfg)
  - Complete source code for all modules
- **Use:** For reviewing code, importing into IDE, or building from source

**Note:** This is a source JAR, not a runnable Minecraft mod. To get the compiled mod JAR, you need to build the project.

## 🔨 Building the Compiled JAR

The compiled JAR (Raspberry-Client-1.0.0.jar) requires building with Gradle and internet access.

### Quick Build (Linux/Mac)

```bash
# From repository root
./build-client.sh
```

This automated script will:
1. Check internet connectivity
2. Verify Java version
3. Setup Minecraft workspace (first time only, 10-30 min)
4. Build the JAR
5. Copy output to release/ folder

### Manual Build

```bash
# Generate Gradle wrapper (first time only)
gradle wrapper --gradle-version 3.1

# Setup workspace (first time only, takes 10-30 minutes)
./gradlew setupDecompWorkspace

# Build the JAR
./gradlew build

# Output will be in: build/libs/Raspberry-Client-1.0.0.jar
```

### Windows Build

```cmd
:: Generate Gradle wrapper (first time only)
gradle wrapper --gradle-version 3.1

:: Setup workspace (first time only)
gradlew.bat setupDecompWorkspace

:: Build the JAR
gradlew.bat build
```

## 📋 Build Requirements

- **Java:** JDK 8 (Java 1.8)
- **Internet:** Required for downloading dependencies
- **RAM:** 3GB+ for Gradle build
- **Time:**
  - First build: 10-30 minutes (workspace setup)
  - Subsequent builds: 30-60 seconds

## 🎯 What You Get After Building

The compiled JAR (build/libs/Raspberry-Client-1.0.0.jar) will be:

- **Size:** ~2-5 MB
- **Contents:**
  - Compiled class files (45+ classes)
  - Embedded Mixin library
  - Mixin refmap (mixins.raspberry.refmap.json)
  - All resource files
  - Proper Forge mod manifest

### Installation

1. Ensure you have Minecraft 1.8.9 with Forge 11.15.1.2318
2. Copy `Raspberry-Client-1.0.0.jar` to `.minecraft/mods/`
3. Launch Minecraft with Forge profile
4. Press Right Shift in-game to open ClickGUI

## 🔍 Verifying the Source JAR

To inspect the source JAR contents:

```bash
# List contents
jar -tf Raspberry-Client-1.0.0-sources.jar

# Extract all files
jar -xf Raspberry-Client-1.0.0-sources.jar

# Extract to specific directory
mkdir extracted && cd extracted
jar -xf ../Raspberry-Client-1.0.0-sources.jar
```

## 📚 What's Included in Source

The source JAR contains the complete implementation:

### Core Systems
- `Raspberry.java` - Main mod class
- `config/ConfigManager.java` - Configuration system
- `mixin/RaspberryMixinLoader.java` - Mixin initialization

### Modules (9 total)
- **Render:** FPS, CPS, Keystrokes, Coordinates
- **Visual:** Fullbright, Zoom
- **Movement:** Toggle Sprint, Toggle Sneak
- **Misc:** ClickGUI

### Support Systems
- **Events:** Event bus and 5 event types
- **Settings:** 3 setting types (Boolean, Number, Mode)
- **GUI:** Complete ClickGUI implementation
- **Mixins:** 10 Mixin injection points
- **Utils:** Rendering and color utilities

## ⚠️ Why Not Include Compiled JAR?

The compiled JAR cannot be pre-built because:

1. **Requires Internet:** Must download ForgeGradle, MixinGradle, Minecraft, and Forge
2. **Large Dependencies:** Workspace setup downloads ~200 MB of files
3. **Build Environment:** Needs specific Java 8 and Gradle 3.1 setup
4. **Mixin Processing:** Requires Mixin transformations during build

The source JAR is provided so you can:
- Review all the code
- Import into your IDE
- Build when you have internet access
- Verify implementation completeness

## 🚀 Quick Start

**For Developers:**
```bash
# Import into IntelliJ IDEA
1. File → Open → Select RaspberryClient folder
2. Wait for Gradle sync
3. Run ./gradlew setupDecompWorkspace
4. Refresh Gradle project
```

**For Players:**
```bash
# Build and install
./build-client.sh
cp build/libs/Raspberry-Client-1.0.0.jar ~/.minecraft/mods/
```

## 📖 Additional Documentation

See repository root for:
- `README.md` - Project overview and features
- `build.gradle` - Build configuration
- Source code with comprehensive JavaDoc comments

---

**Status:** Source code complete, ready to build
**Version:** 1.0.0
**Target:** Minecraft 1.8.9 + Forge 11.15.1.2318
