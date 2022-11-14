package net.pl3x.servergui.test;

import net.pl3x.servergui.api.ServerGUI;
import net.pl3x.servergui.test.command.ScreenCommand;
import net.pl3x.servergui.test.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerGUITest extends JavaPlugin {
    public void onEnable() {
        getCommand("screen").setExecutor(new ScreenCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        // preload any textures to be used
        ServerGUI.api().getTextureManager().add("test:billy", "https://avatars.githubusercontent.com/u/332527?v=4");
        ServerGUI.api().getTextureManager().add("test:github", "https://cdn.onlinewebfonts.com/svg/img_4266.png");
        ServerGUI.api().getTextureManager().add("test:hayley", "https://pl3x.net/hayley.png");
        ServerGUI.api().getTextureManager().add("test:gradient", "https://i.imgur.com/9oUV5Mb.png");
    }
}
