package com.raspberry.client.mixins;

import com.raspberry.client.events.EventManager;
import com.raspberry.client.events.types.EventRender3D;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for the EntityRenderer class.
 * Hooks into rendering events for 3D world rendering.
 */
@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    /**
     * Injects into the renderWorldPass method to fire 3D render events.
     * @param pass the render pass
     * @param partialTicks partial ticks
     * @param finishTimeNano finish time
     * @param ci callback info
     */
    @Inject(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z", shift = At.Shift.BEFORE))
    private void onRenderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci) {
        EventManager.call(new EventRender3D(partialTicks));
    }
}
