package net.pl3x.guithium.test.command;

import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.player.WrappedPlayer;
import net.pl3x.guithium.test.gui.AnchorsScreen;
import net.pl3x.guithium.test.gui.Calculator;
import net.pl3x.guithium.test.gui.SampleScreen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ScreenCommand implements TabExecutor {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // only let players use this command
        if (!(sender instanceof Player bukkitPlayer)) {
            sender.sendMessage("Player only command.");
            return true;
        }

        // get the wrapped player from Guithium and verify they have the mod installed
        WrappedPlayer wrappedPlayer = Guithium.api().getPlayerManager().get(bukkitPlayer.getUniqueId());
        if (!wrappedPlayer.hasGuithium()) {
            sender.sendMessage("You must have Guithium installed to use this command");
            return true;
        }

        // create a new screen based on command used
        Screen screen;
        String cmd = label.toLowerCase(Locale.ROOT);
        if (cmd.startsWith("calc")) {
            screen = new Calculator();
        } else if (cmd.equals("anchors")) {
            screen = new AnchorsScreen();
        } else {
            screen = new SampleScreen();
        }

        // open the screen on the player's client
        screen.open(wrappedPlayer);
        return true;
    }
}
