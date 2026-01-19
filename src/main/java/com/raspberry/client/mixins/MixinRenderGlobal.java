package com.raspberry.client.mixins;

import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Mixin for the RenderGlobal class.
 * Used for world rendering modifications (block overlays, etc.).
 */
@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {
    // Future: Add block overlay and rendering modifications here
}
