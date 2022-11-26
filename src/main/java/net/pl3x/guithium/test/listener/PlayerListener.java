package net.pl3x.guithium.test.listener;

import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.action.ActionHandler;
import net.pl3x.guithium.api.action.ActionListener;
import net.pl3x.guithium.api.action.player.PlayerJoinedAction;
import net.pl3x.guithium.api.player.WrappedPlayer;
import net.pl3x.guithium.test.gui.CoordsHud;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements ActionListener, Listener {
    private final Map<UUID, CoordsHud> coordsHuds = new HashMap<>();

    // listen to Guithium actions

    @ActionHandler
    public void onPlayerJoined(PlayerJoinedAction action) {
        WrappedPlayer player = action.getPlayer();

        // create and open new player coord hud
        CoordsHud hud = new CoordsHud(player);
        hud.open(player);

        // store it for later use
        this.coordsHuds.put(player.getUUID(), hud);
    }

    // listen to Bukkit events

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        // remove the player's coord hud
        this.coordsHuds.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        // check if player actually moved to a new block
        if (!event.hasChangedBlock()) {
            return;
        }

        // get the player and their coords hud
        WrappedPlayer player = Guithium.api().getPlayerManager().get(event.getPlayer().getUniqueId());
        CoordsHud hud = this.coordsHuds.get(player.getUUID());

        // up the coords on the hud
        hud.update(event.getTo());
    }
}
