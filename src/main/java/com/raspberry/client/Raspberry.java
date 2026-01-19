package com.raspberry.client;

import com.raspberry.client.config.ConfigManager;
import com.raspberry.client.modules.ModuleManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Main class for the Raspberry Client.
 * A professional, high-performance Minecraft 1.8.9 PVP client.
 */
@Mod(modid = Raspberry.MOD_ID, name = Raspberry.MOD_NAME, version = Raspberry.VERSION, clientSideOnly = true)
public class Raspberry {

    public static final String MOD_ID = "raspberry";
    public static final String MOD_NAME = "Raspberry Client";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MOD_ID)
    private static Raspberry instance;

    private ModuleManager moduleManager;
    private ConfigManager configManager;

    private boolean configLoaded = false;

    /**
     * Gets the Raspberry client instance.
     * @return the client instance
     */
    public static Raspberry getInstance() {
        return instance;
    }

    /**
     * Pre-initialization phase.
     * @param event the pre-init event
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("========================================");
        System.out.println("  Raspberry Client v" + VERSION);
        System.out.println("  Professional PVP Client for MC 1.8.9");
        System.out.println("========================================");
    }

    /**
     * Initialization phase.
     * @param event the init event
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("[Raspberry] Initializing client...");

        // Initialize module manager
        moduleManager = new ModuleManager();
        moduleManager.initialize();

        // Initialize config manager
        configManager = new ConfigManager();

        // Register this instance to Forge event bus
        MinecraftForge.EVENT_BUS.register(this);

        // Add shutdown hook to save config
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("[Raspberry] Saving configuration...");
            configManager.saveConfig();
        }));

        System.out.println("[Raspberry] Client initialized successfully!");
    }

    /**
     * Handles client tick events to load config after world is ready.
     * @param event the tick event
     */
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (!configLoaded && event.phase == TickEvent.Phase.END) {
            configLoaded = true;
            configManager.loadConfig();
        }
    }

    /**
     * Gets the module manager.
     * @return the module manager
     */
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    /**
     * Gets the config manager.
     * @return the config manager
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }
}
