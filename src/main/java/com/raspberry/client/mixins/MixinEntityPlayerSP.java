package com.raspberry.client.mixins;

import com.raspberry.client.events.Event;
import com.raspberry.client.events.EventManager;
import com.raspberry.client.events.types.EventUpdate;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for the EntityPlayerSP class.
 * Hooks into the player update method to fire update events.
 */
@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {

    /**
     * Injects at the head of onUpdate to fire PRE update event.
     * @param ci callback info
     */
    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onUpdatePre(CallbackInfo ci) {
        EventManager.call(new EventUpdate(Event.Phase.PRE));
    }

    /**
     * Injects at the return of onUpdate to fire POST update event.
     * @param ci callback info
     */
    @Inject(method = "onUpdate", at = @At("RETURN"))
    private void onUpdatePost(CallbackInfo ci) {
        EventManager.call(new EventUpdate(Event.Phase.POST));
    }
}
