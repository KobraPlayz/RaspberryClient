package com.raspberry.client.mixins;

import com.raspberry.client.events.EventManager;
import com.raspberry.client.events.types.EventRender2D;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for the GuiIngame class.
 * Hooks into the HUD rendering to fire 2D render events.
 */
@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    /**
     * Injects into the renderGameOverlay method to fire 2D render events.
     * @param partialTicks partial ticks
     * @param ci callback info
     */
    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void onRenderGameOverlay(float partialTicks, CallbackInfo ci) {
        EventManager.call(new EventRender2D(partialTicks));
    }
}
