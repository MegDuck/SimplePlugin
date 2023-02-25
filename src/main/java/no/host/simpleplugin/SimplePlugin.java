package no.host.simpleplugin;

import net.kyori.adventure.text.Component;
import no.host.simpleplugin.commands.*;
import no.host.simpleplugin.event.EventListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimplePlugin extends JavaPlugin {
    public static JavaPlugin plugin;

    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        this.getServer().getPluginManager().registerEvents(new EventListener(), plugin);

        this.parseConfig();
        this.registerCommands();
    }

    private void parseConfig() {
        config.addDefault("chat.joinserver", "join the server!");
        config.addDefault("chat.leaveserver", "left the server!");
        saveConfig();

        String joinMessage = getConfig().getString("chat.joinserver");
        if(joinMessage != null) {
            EventListener.joinMessage = Component.text(joinMessage);
        }

        String leaveMessage = getConfig().getString("chat.leaveserver");
        if(leaveMessage != null) {
            EventListener.leaveMessage = Component.text(leaveMessage);
        }
    }

    private void registerCommands() {
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("setwarp").setExecutor(new SetWarpCommand());
        this.getCommand("warp").setExecutor(new WarpCommand());
        this.getCommand("survival").setExecutor(new SurvivalCommand());
        this.getCommand("spectator").setExecutor(new SpectatorCommand());
        this.getCommand("creative").setExecutor(new CreativeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
