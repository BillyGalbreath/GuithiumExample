package net.pl3x.servergui.test;

import net.pl3x.servergui.api.gui.Gui;
import net.pl3x.servergui.api.gui.element.Text;
import net.pl3x.servergui.plugin.network.packet.GuiPacket;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CoordsHUD extends Gui {
    private final Text coords;

    public CoordsHUD() {
        super("test:hud", Type.HUD);
        this.coords = Text.builder("test:hud-coords", "0, 0, 0").build();
        addElement(this.coords);
    }

    public void update(Player player, Location loc) {
        this.coords.setText(loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ());
        new GuiPacket().send(player, this);
    }
}
