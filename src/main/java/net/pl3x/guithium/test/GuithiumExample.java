package net.pl3x.guithium.test;

import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.texture.Texture;
import net.pl3x.guithium.api.gui.texture.TextureManager;
import net.pl3x.guithium.test.command.CalculatorCommand;
import net.pl3x.guithium.test.command.ScreenCommand;
import net.pl3x.guithium.test.listener.PlayerListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class GuithiumExample extends JavaPlugin {
    public static final Texture HAYLEY = Texture.of("test:hayley", "https://pl3x.net/hayley.png");

    public void onEnable() {
        // register commands
        registerCommand("calculator", new CalculatorCommand());
        registerCommand("screen", new ScreenCommand());

        // register listeners
        PlayerListener playerListener = new PlayerListener();
        Guithium.api().getActionRegistry().register(playerListener);
        getServer().getPluginManager().registerEvents(playerListener, this);

        // preload any textures to be used
        TextureManager textureManager = Guithium.api().getTextureManager();
        textureManager.add(HAYLEY);
    }

    private void registerCommand(String commandName, CommandExecutor executor) {
        PluginCommand command = getCommand(commandName);
        if (command != null) {
            command.setExecutor(executor);
        }
    }
}
