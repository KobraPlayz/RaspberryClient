package com.raspberry.client.mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

/**
 * Mixin loader for Raspberry Client.
 * This class initializes the Mixin system and registers our mixin configuration.
 */
@IFMLLoadingPlugin.MCVersion("1.8.9")
public class RaspberryMixinLoader implements IFMLLoadingPlugin {

    public RaspberryMixinLoader() {
        System.out.println("[Raspberry] Initializing Mixin system...");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.raspberry.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        System.out.println("[Raspberry] Mixin system initialized successfully!");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
