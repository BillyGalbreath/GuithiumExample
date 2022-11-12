package net.pl3x.servergui.test;

import net.pl3x.servergui.api.ServerGUI;
import net.pl3x.servergui.api.gui.Gui;
import net.pl3x.servergui.api.gui.element.Image;
import net.pl3x.servergui.api.gui.element.Point;
import net.pl3x.servergui.api.gui.element.Text;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerGUITest extends JavaPlugin {
    private final GuiManager guiManager;

    public ServerGUITest() {
        super();
        this.guiManager = new GuiManager();
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        // preload any textures to be used
        ServerGUI.api().getTextureManager().add("test:billy", "https://avatars.githubusercontent.com/u/332527?v=4");
        ServerGUI.api().getTextureManager().add("test:github", "https://cdn.onlinewebfonts.com/svg/img_4266.png");
        ServerGUI.api().getTextureManager().add("test:hayley", "https://www.pngplay.com/wp-content/uploads/13/Hayley-Williams-Download-Free-PNG.png");

        // set up a simple hud with basic elements
        Gui gui = new Gui("testplugin:testhud", Gui.Type.HUD);
        gui.addElement(Image.builder("test:hayley")
            .setSize(Point.of(120, 150))
            .setPos(Point.of(0, 20))
            .build());
        gui.addElement(Text.builder("text2", "O")
            .setAnchor(Point.of(0.5F, 0.5F))
            .setOffset(Point.of(0.5F, 0.5F))
            .setShadow(false)
            .build());
        gui.addElement(Text.builder("text3", "bottom right")
            .setAnchor(Point.of(1, 1))
            .setOffset(Point.of(1, 1))
            .build());

        // any Gui added to the manager will be automatically sent to the player when they join
        ServerGUI.api().getGuiManager().add(gui);
    }

    public GuiManager getGuiManager() {
        return this.guiManager;
    }
}
