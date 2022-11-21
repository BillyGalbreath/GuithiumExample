package net.pl3x.guithium.test.listener;

import net.kyori.adventure.text.Component;
import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.action.ActionHandler;
import net.pl3x.guithium.api.action.ActionListener;
import net.pl3x.guithium.api.action.player.PlayerJoinedAction;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Element;
import net.pl3x.guithium.api.gui.element.Text;
import net.pl3x.guithium.api.player.WrappedPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements ActionListener, Listener {
    private final Map<UUID, Screen> huds = new HashMap<>();

    // Listen to Guithium Actions

    @ActionHandler
    public void onPlayerJoined(PlayerJoinedAction action) {
        WrappedPlayer player = action.getPlayer();

        // update the coords on the hud
        updateHud(player, player.<Player>unwrap().getLocation());
    }

    // Listen to Bukkit Events

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.huds.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        // check if player actually moved to a new block
        if (event.hasChangedBlock()) {
            WrappedPlayer player = Guithium.api().getPlayerManager().get(event.getPlayer().getUniqueId());
            // up the coords on the hud
            updateHud(player, event.getTo());
        }
    }

    // Common methods

    private void updateHud(WrappedPlayer player, Location loc) {
        // get the player's current coords hud
        Screen hud = this.huds.get(player.getUUID());

        // ensure the hud exists
        if (hud == null) {
            hud = createHud(player);
        }

        // get the coords element
        Element element = hud.getElement("test:coords");

        // make sure text element exists and is a text element
        if (element instanceof Text text) {
            // update the text
            String coords = loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ();
            text.setText(Component.text(coords));

            // send the updated text element to the player
            text.send(player);
        }
    }

    private Screen createHud(WrappedPlayer player) {
        // build a new hud screen
        Screen hud = Screen.builder("test:hud")
            .setType(Screen.Type.HUD)
            .build();

        // add the coords text element
        hud.addElement(
            Text.builder("test:coords")
                .build()
        );

        // send the hud to the player
        hud.open(player);

        // store the hud
        this.huds.put(player.getUUID(), hud);

        // return the new hud
        return hud;
    }
}
