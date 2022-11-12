package net.pl3x.servergui.test;

import net.pl3x.servergui.plugin.event.HelloEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private final ServerGUITest plugin;

    public PlayerListener(ServerGUITest plugin) {
        this.plugin = plugin;
    }

    // send our own Gui list manually to the player when they join.
    // we use the HelloEvent instead of the PlayerJoinEvent here because
    // the join event fires too soon in the connection state and is not
    // able to properly send things to the client. the hello event will
    // only fire once the client is ready to send _and_ receive data.
    @EventHandler
    public void onJoin(HelloEvent event) {
        Player player = event.getPlayer();
        this.plugin.getGuiManager()
            .getHud(player.getUniqueId())
            .update(player, player.getLocation());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.plugin.getGuiManager().removeHud(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.hasChangedBlock()) {
            Player player = event.getPlayer();
            this.plugin.getGuiManager()
                .getHud(player.getUniqueId())
                .update(player, event.getTo());
        }
    }
}
