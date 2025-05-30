package net.pl3x.guithium.test.listener;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.action.ActionListener;
import net.pl3x.guithium.api.action.actions.player.PlayerJoinedAction;
import net.pl3x.guithium.api.action.actions.player.screen.element.ElementClickedAction;
import net.pl3x.guithium.api.action.actions.player.screen.element.ElementToggledAction;
import net.pl3x.guithium.api.action.actions.player.screen.element.ElementValueChangedAction;
import net.pl3x.guithium.api.player.WrappedPlayer;
import net.pl3x.guithium.test.GuithiumExample;
import net.pl3x.guithium.test.gui.CoordsHud;
import net.pl3x.guithium.test.gui.StatsHud;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

    @ActionHandler
    public void onElementClicked(ElementClickedAction<?> action) {
        this.plugin.getLogger().warning("Element Clicked:" + action.getElement().getClass().getSimpleName());
        this.plugin.getLogger().warning("      - " + action.getElement().getClass().getSimpleName());
    }

    @ActionHandler
    public void onElementToggled(ElementToggledAction<?> action) {
        this.plugin.getLogger().warning("Element Toggled:");
        this.plugin.getLogger().warning("      - " + action.getElement().getClass().getSimpleName());
        this.plugin.getLogger().warning("      - " + action.isSelected());
    }

    @ActionHandler
    public void onElementValueChanged(ElementValueChangedAction<?, ?> action) {
        this.plugin.getLogger().warning("Element Value Changed:");
        this.plugin.getLogger().warning("      - " + action.getElement().getClass().getSimpleName());
        this.plugin.getLogger().warning("      - " + action.getValue() + " (" + action.getValue().getClass().getSimpleName() + ")");
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
        handleStatsChange(event.getEntity());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onHealthChange(EntityRegainHealthEvent event) {
        handleStatsChange(event.getEntity());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onHealthChange(PlayerPostRespawnEvent event) {
        handleStatsChange(event.getPlayer());
    }

    private void handleStatsChange(Entity entity) {
        if (!(entity instanceof Player)) {
            return;
        }

        StatsHud statsHud = this.statsHuds.get(entity.getUniqueId());
        if (statsHud == null) {
            return;
        }

        Bukkit.getScheduler().runTaskLater(this.plugin, statsHud::update, 1L);
    }
}
