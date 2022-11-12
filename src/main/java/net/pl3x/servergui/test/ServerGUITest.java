package net.pl3x.servergui.test;

import net.pl3x.servergui.api.ServerGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerGUITest extends JavaPlugin {
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        // preload any textures to be used
        ServerGUI.api().getTextureManager().add("test:billy", "https://avatars.githubusercontent.com/u/332527?v=4");
        ServerGUI.api().getTextureManager().add("test:github", "https://cdn.onlinewebfonts.com/svg/img_4266.png");
        ServerGUI.api().getTextureManager().add("test:hayley", "https://www.pngplay.com/wp-content/uploads/13/Hayley-Williams-Download-Free-PNG.png");
    }
}
