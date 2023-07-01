package net.pl3x.guithium.test.gui;

import net.kyori.adventure.text.Component;
import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Text;
import net.pl3x.guithium.api.player.WrappedPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CoordsHud extends Screen {
    private final WrappedPlayer player;
    private final Text text;

    public CoordsHud(WrappedPlayer player) {
        super(Key.of("test:coords_hud"), Type.HUD);
        this.player = player;

        // create and add text element to the screen
        this.text = Text.builder("test:coords")
                .setPos(3, 1)
                .build();
        addElement(this.text);

        // use player's current location for initial value
        update(player.<Player>unwrap().getLocation());
    }

    public void update(Location loc) {
        // update the text
        String coords = loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ();
        this.text.setText(Component.text(coords));

        // send just the updated text element to the player's client
        this.text.send(this.player);
    }
}
