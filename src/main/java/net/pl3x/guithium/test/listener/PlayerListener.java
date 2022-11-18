package net.pl3x.guithium.test.listener;

import net.kyori.adventure.text.Component;
import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Element;
import net.pl3x.guithium.api.gui.element.Text;
import net.pl3x.guithium.api.player.Player;
import net.pl3x.guithium.plugin.event.HelloEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {
    private final Map<UUID, Screen> huds = new HashMap<>();

    @EventHandler
    public void onHello(HelloEvent event) {
        org.bukkit.entity.Player bukkit = event.getPlayer();

        Player player = Guithium.api().getPlayerManager().get(bukkit.getUniqueId());

        // update the coords on the hud
        updateHud(player, bukkit.getLocation());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.huds.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        // check if player actually moved to a new block
        if (event.hasChangedBlock()) {
            Player player = Guithium.api().getPlayerManager().get(event.getPlayer().getUniqueId());
            // up the coords on the hud
            updateHud(player, event.getTo());
        }
    }

    private Screen createHud(Player player) {
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

    private void updateHud(Player player, Location loc) {
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
}
