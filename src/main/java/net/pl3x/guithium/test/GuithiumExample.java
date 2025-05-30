package net.pl3x.guithium.test;

import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.hud.Render;
import net.pl3x.guithium.api.gui.texture.Texture;
import net.pl3x.guithium.api.gui.texture.TextureManager;
import net.pl3x.guithium.test.command.ScreenCommand;
import net.pl3x.guithium.test.gui.StatsHud;
import net.pl3x.guithium.test.listener.PlayerListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class GuithiumExample extends JavaPlugin {
    public static final Texture BANANA = Texture.of("test:banana", "https://www.gifcen.com/wp-content/uploads/2022/09/png-gif.gif");
    public static final Texture HAYLEY = Texture.of("test:hayley", "https://pl3x.net/hayley.png");

    public void onEnable() {
        // register commands
        registerCommand("anchors", new ScreenCommand());
        registerCommand("calculator", new ScreenCommand());
        registerCommand("screen", new ScreenCommand());

        // register listeners
        PlayerListener playerListener = new PlayerListener(this);
        Guithium.api().getActionRegistry().register(playerListener);
        getServer().getPluginManager().registerEvents(playerListener, this);

        Guithium.api().getHudManager().getGlobalSettings()
                .set(Render.PLAYER_HEALTH, false)
                .set(Render.SLEEP_OVERLAY, false)
                .set(Render.CAMERA_OVERLAYS, false);

        // preload any textures to be used
        TextureManager textureManager = Guithium.api().getTextureManager();
        textureManager.add(BANANA);
        textureManager.add(HAYLEY);
        textureManager.add(StatsHud.PROGRESSBAR);
    }

    private void registerCommand(String commandName, CommandExecutor executor) {
        PluginCommand command = getCommand(commandName);
        if (command != null) {
            command.setExecutor(executor);
        }
    }
}
