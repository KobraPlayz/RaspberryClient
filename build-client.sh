#!/bin/bash

# Raspberry Client Build Script
# Builds the complete Minecraft mod JAR with all dependencies

set -e

echo "========================================="
echo "  Raspberry Client v1.0.0 Build Script"
echo "========================================="
echo ""

# Check for internet connectivity
echo "[1/4] Checking internet connection..."
if ! ping -c 1 google.com &> /dev/null; then
    echo "ERROR: No internet connection detected!"
    echo "This build requires internet to download:"
    echo "  - ForgeGradle 2.1-SNAPSHOT"
    echo "  - MixinGradle 0.6-SNAPSHOT"
    echo "  - Minecraft Forge 1.8.9-11.15.1.2318"
    echo "  - SpongePowered Mixin 0.7.11"
    exit 1
fi
echo "✓ Internet connection OK"
echo ""

# Check Java version
echo "[2/4] Checking Java version..."
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1)
if [ "$JAVA_VERSION" != "1" ] && [ "$JAVA_VERSION" != "8" ]; then
    echo "WARNING: Java 8 is recommended for Minecraft 1.8.9 mods"
    echo "Current version: $JAVA_VERSION"
    echo "Continuing anyway..."
fi
echo "✓ Java version: $JAVA_VERSION"
echo ""

# Setup workspace (only needed once)
if [ ! -d ".gradle" ]; then
    echo "[3/4] Setting up Minecraft workspace (this takes 10-30 minutes)..."
    echo "Generating Gradle wrapper..."
    gradle wrapper --gradle-version 3.1

    echo "Setting up decompiled workspace..."
    ./gradlew setupDecompWorkspace --refresh-dependencies
    echo "✓ Workspace setup complete"
else
    echo "[3/4] Workspace already setup, skipping..."
fi
echo ""

# Build the JAR
echo "[4/4] Building Raspberry-Client JAR..."
./gradlew clean build

echo ""
echo "========================================="
echo "  Build Complete!"
echo "========================================="
echo ""
echo "Output JAR: build/libs/Raspberry-Client-1.0.0.jar"
echo ""

# Copy to release folder
if [ -f "build/libs/Raspberry-Client-1.0.0.jar" ]; then
    cp build/libs/Raspberry-Client-1.0.0.jar release/
    echo "✓ JAR copied to release/Raspberry-Client-1.0.0.jar"
    echo ""
    ls -lh release/*.jar
    echo ""
    echo "To install: Copy release/Raspberry-Client-1.0.0.jar to .minecraft/mods/"
else
    echo "ERROR: Build failed, JAR not found!"
    exit 1
fi
