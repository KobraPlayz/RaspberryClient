package com.raspberry.client.mixins;

import com.raspberry.client.events.EventManager;
import com.raspberry.client.events.types.EventKey;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for the Minecraft class.
 * Hooks into key press events and game loop.
 */
@Mixin(Minecraft.class)
public class MixinMinecraft {

    /**
     * Injects into the runTick method to fire key events.
     * @param ci callback info
     */
    @Inject(method = "runTick", at = @At("RETURN"))
    private void onRunTick(CallbackInfo ci) {
        // Fire key events
        if (Keyboard.getEventKeyState()) {
            int keyCode = Keyboard.getEventKey();
            if (keyCode != 0) {
                EventManager.call(new EventKey(keyCode));
            }
        }
    }

    /**
     * Injects into the shutdown method for cleanup.
     * @param ci callback info
     */
    @Inject(method = "shutdown", at = @At("HEAD"))
    private void onShutdown(CallbackInfo ci) {
        System.out.println("[Raspberry] Shutting down client...");
    }
}
