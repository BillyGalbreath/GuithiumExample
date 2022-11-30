package net.pl3x.guithium.test.listener;

import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.action.ActionHandler;
import net.pl3x.guithium.api.action.ActionListener;
import net.pl3x.guithium.api.action.actions.PlayerJoinedAction;
import net.pl3x.guithium.api.player.WrappedPlayer;
import net.pl3x.guithium.test.GuithiumExample;
import net.pl3x.guithium.test.gui.CoordsHud;
import net.pl3x.guithium.test.gui.StatsHud;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements ActionListener, Listener {
    private final GuithiumExample plugin;

    private final Map<UUID, StatsHud> statsHuds = new HashMap<>();
    private final Map<UUID, CoordsHud> coordsHuds = new HashMap<>();

    public PlayerListener(GuithiumExample plugin) {
        this.plugin = plugin;
    }

    // listen to Guithium actions

    @ActionHandler
    public void onPlayerJoined(PlayerJoinedAction action) {
        WrappedPlayer player = action.getPlayer();

        StatsHud stats = new StatsHud(player);
        stats.open(player);
        this.statsHuds.put(player.getUUID(), stats);

        // create and open new player coord hud
        CoordsHud hud = new CoordsHud(player);
        hud.open(player);
        this.coordsHuds.put(player.getUUID(), hud);
    }

    // listen to Bukkit events

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        // remove the player's huds
        this.coordsHuds.remove(uuid);
        this.statsHuds.remove(uuid);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        // check if player actually moved to a new block
        if (!event.hasChangedBlock()) {
            return;
        }

        // get the player and their coords hud
        WrappedPlayer player = Guithium.api().getPlayerManager().get(event.getPlayer());
        CoordsHud hud = this.coordsHuds.get(player.getUUID());

        // up the coords on the hud
        hud.update(event.getTo());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onHealthChange(EntityDamageEvent event) {
        handleStatsChange(event);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onHealthChange(EntityRegainHealthEvent event) {
        handleStatsChange(event);
    }

    private void handleStatsChange(EntityEvent event) {
        if (event.getEntityType() != EntityType.PLAYER) {
            return;
        }

        WrappedPlayer player = Guithium.api().getPlayerManager().get(event.getEntity());
        if (player == null) {
            return;
        }

        StatsHud statsHud = this.statsHuds.get(player.getUUID());
        if (statsHud == null) {
            return;
        }

        Bukkit.getScheduler().runTaskLater(this.plugin, statsHud::update, 1L);
    }
}
