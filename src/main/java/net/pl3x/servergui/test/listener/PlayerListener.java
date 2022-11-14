package net.pl3x.servergui.test.listener;

import net.pl3x.servergui.api.gui.Screen;
import net.pl3x.servergui.api.gui.element.Element;
import net.pl3x.servergui.api.gui.element.Text;
import net.pl3x.servergui.plugin.event.HelloEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class PlayerListener implements Listener {
    private final Map<Player, Screen> huds = new HashMap<>();

    @EventHandler
    public void onHello(HelloEvent event) {
        Player player = event.getPlayer();
        // update the coords on the hud
        updateHud(player, player.getLocation());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        // check if player actually moved to a new block
        if (event.hasChangedBlock()) {
            // up the coords on the hud
            updateHud(event.getPlayer(), event.getTo());
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
        hud.send(player.getUniqueId());

        // store the hudt
        this.huds.put(player, hud);

        // return the new hud
        return hud;
    }

    private void updateHud(Player player, Location loc) {
        // get the player's current coords hud
        Screen hud = this.huds.get(player);

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
            text.setText(coords);

            // send the updated text element to the player
            text.send(player.getUniqueId());
        }
    }
}
