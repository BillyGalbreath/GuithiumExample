package net.pl3x.servergui.test;

import net.pl3x.servergui.api.gui.element.Image;
import net.pl3x.servergui.api.gui.element.Text;
import net.pl3x.servergui.plugin.event.HelloEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class PlayerListener implements Listener {
    @EventHandler
    public void onHello(HelloEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        updateCoordsHUD(player, player.getLocation());

        // test stuff
        Image.builder("test:hayley")
            .setSize(120, 150)
            .setPos(0, 20)
            .build()
            .send(uuid);
        Text.builder("test:crosshair")
            .setText("O")
            .setAnchor(0.5F, 0.5F)
            .setOffset(0.5F, 0.5F)
            .setShadow(false)
            .build()
            .send(uuid);
        Text.builder("test:footnote")
            .setText("bottom right")
            .setAnchor(1, 1)
            .setOffset(1, 1)
            .build()
            .send(uuid);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.hasChangedBlock()) {
            updateCoordsHUD(event.getPlayer(), event.getTo());
        }
    }

    private void updateCoordsHUD(Player player, Location loc) {
        String coords = loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ();
        Text.builder("test:coords")
            .setText(coords)
            .build()
            .send(player.getUniqueId());
    }
}
