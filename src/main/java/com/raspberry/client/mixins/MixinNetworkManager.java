package com.raspberry.client.mixins;

import com.raspberry.client.events.EventManager;
import com.raspberry.client.events.types.EventPacket;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for the NetworkManager class.
 * Hooks into packet sending and receiving.
 */
@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    /**
     * Injects into sendPacket to fire packet send events.
     * @param packet the packet being sent
     * @param ci callback info
     */
    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, CallbackInfo ci) {
        EventPacket event = new EventPacket(packet, EventPacket.Type.SEND);
        EventManager.call(event);

        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    /**
     * Injects into channelRead0 to fire packet receive events.
     * @param context channel context
     * @param packet the packet being received
     * @param ci callback info
     */
    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onReceivePacket(ChannelHandlerContext context, Packet<?> packet, CallbackInfo ci) {
        EventPacket event = new EventPacket(packet, EventPacket.Type.RECEIVE);
        EventManager.call(event);

        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}
