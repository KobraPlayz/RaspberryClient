package com.raspberry.client.mixins;

import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Mixin for the ItemRenderer class.
 * Used for item and hand rendering modifications (1.7 animations).
 */
@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
    // Future: Add 1.7 animation support here
}
