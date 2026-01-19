package com.raspberry.client.mixins;

import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Mixin for the WorldClient class.
 * Used for world-related modifications.
 */
@Mixin(WorldClient.class)
public class MixinWorldClient {
    // Future: Add world-related modifications here
}
