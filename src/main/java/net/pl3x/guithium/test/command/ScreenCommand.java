package net.pl3x.guithium.test.command;

import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Button;
import net.pl3x.guithium.api.gui.element.Element;
import net.pl3x.guithium.api.gui.element.Image;
import net.pl3x.guithium.api.gui.element.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
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
        if (!(sender instanceof org.bukkit.entity.Player bukkit)) {
            sender.sendMessage("Player only command.");
            return true;
        }

        // populate screen with elements
        List<Element> elements = List.of(
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
                .build(),
            Button.builder("test:button")
                .setText("Click Me")
                .setPos(0, 20)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onClick((screen, button, player) -> {
                    System.out.println("onClick fired");
                    screen.close(player);
                })
                .build()
        );

        // create a new screen
        Screen screen = Screen.builder("test:screen")
            .setBackground(Screen.Background.GRADIENT)
            .build();

        // add elements to the screen
        screen.addElements(elements);

        // open screen to the player
        screen.open(Guithium.api().getPlayerManager().get(bukkit.getUniqueId()));
        return true;
    }
}
