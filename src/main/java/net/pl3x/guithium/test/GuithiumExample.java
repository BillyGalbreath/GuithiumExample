package net.pl3x.guithium.test;

import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.texture.Texture;
import net.pl3x.guithium.api.gui.texture.TextureManager;
import net.pl3x.guithium.test.command.ScreenCommand;
import net.pl3x.guithium.test.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GuithiumExample extends JavaPlugin {
    public static final Texture HAYLEY = Texture.of("test:hayley", "https://pl3x.net/hayley.png");

    public void onEnable() {
        getCommand("screen").setExecutor(new ScreenCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        // preload any textures to be used
        TextureManager textureManager = Guithium.api().getTextureManager();
        textureManager.add(HAYLEY);
    }
}
