package net.pl3x.servergui.test.command;

import net.pl3x.servergui.api.gui.Screen;
import net.pl3x.servergui.api.gui.element.Image;
import net.pl3x.servergui.api.gui.element.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ScreenCommand implements TabExecutor {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only command.");
            return true;
        }

        // create a new screen
        Screen screen = Screen.builder("test:screen")
            .setBackground(Screen.Background.TEXTURE)
            .build();

        // populate screen with elements
        screen.addElements(List.of(
            Image.builder("test:hayley")
                .setSize(120, 150)
                .setPos(0, 20)
                .build(),
            Image.builder("test:gradient")
                .setSize(100, 100)
                .setPos(100, 100)
                .build(),
            Text.builder("test:crosshair")
                .setText("O")
                .setAnchor(0.5F, 0.5F)
                .setOffset(0.5F, 0.5F)
                .setShadow(false)
                .build(),
            Text.builder("test:footnote")
                .setText("bottom right")
                .setAnchor(1, 1)
                .setOffset(1, 1)
                .build()
        ));

        // send screen to the player
        screen.send(player.getUniqueId());
        return true;
    }
}
