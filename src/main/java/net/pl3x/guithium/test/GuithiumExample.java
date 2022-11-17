package net.pl3x.guithium.test;

import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.texture.TextureManager;
import net.pl3x.guithium.test.command.ScreenCommand;
import net.pl3x.guithium.test.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GuithiumExample extends JavaPlugin {
    public void onEnable() {
        getCommand("screen").setExecutor(new ScreenCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        // preload any textures to be used
        TextureManager textureManager = Guithium.api().getTextureManager();
        textureManager.add("test:billy", "https://avatars.githubusercontent.com/u/332527?v=4");
        textureManager.add("test:github", "https://cdn.onlinewebfonts.com/svg/img_4266.png");
        textureManager.add("test:hayley", "https://pl3x.net/hayley.png");
        textureManager.add("test:gradient", "https://i.imgur.com/9oUV5Mb.png");
    }
}
