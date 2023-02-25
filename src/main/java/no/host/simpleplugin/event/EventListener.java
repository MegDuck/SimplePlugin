package no.host.simpleplugin.event;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    public static Component joinMessage=Component.text("joined the server");
    public static Component leaveMessage=Component.text("left the server");

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        event.joinMessage(event.getPlayer().displayName().append(joinMessage));
    }

    @EventHandler
    public void onPlayerLeave(final PlayerQuitEvent event) {
        event.quitMessage(event.getPlayer().displayName().append(leaveMessage));
    }

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        Player player = event.getPlayer();

        if(!player.hasPermission("simple.breakblock")) {
            event.setCancelled(true);
        }
    }
}
