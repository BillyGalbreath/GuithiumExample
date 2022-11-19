package net.pl3x.guithium.test.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Button;
import net.pl3x.guithium.api.gui.element.Element;
import net.pl3x.guithium.api.gui.element.Image;
import net.pl3x.guithium.api.gui.element.Text;
import net.pl3x.guithium.api.gui.texture.Texture;
import net.pl3x.guithium.api.player.Player;
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
        if (!(sender instanceof org.bukkit.entity.Player bukkitPlayer)) {
            sender.sendMessage("Player only command.");
            return true;
        }

        // populate screen with elements
        List<Element> elements = List.of(
            // mmmm, hayley ^_^
            Image.builder("test:hayley")
                .setSize(120, 150)
                .setPos(0, 20)
                .setTexture(
                    new Texture(Key.of("test:hayley"), "https://pl3x.net/hayley.png")
                )
                .build(),
            // some text centered in the screen
            Text.builder("test:centered_text")
                .setText(Component.text("Centered Text"))
                .setAnchor(0.5F, 0.5F)
                .setOffset(0.5F, 0.5F)
                .setShadow(false)
                .build(),
            // some text at the bottom right of the screen
            Text.builder("test:footnote")
                .setText(Component.text("bottom right"))
                .setAnchor(1, 1)
                .setOffset(1, 1)
                .build(),
            // a clickable button at the top center
            Button.builder("test:button")
                .setText("Click Me")
                .setPos(0, 20)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onClick((screen, button, player) -> {
                    // this code will fire when the button is clicked
                    System.out.println("onClick fired!");
                    // let's close the screen on the player's client
                    screen.close(player);
                })
                .setTooltip(MiniMessage.miniMessage().deserialize("<green>Testing <b><blue>testing</blue> 1 \n2</b> <red>3</red> 4 5"))
                .build()
        );

        // create a new screen with custom gradient background
        Screen screen = Screen.builder("test:screen")
            .setBackground(Image.TILED_DIRT)
            /*.setBackground(
                Gradient.builder("test:background")
                    .setColorTopLeft(0xC0FF0000)
                    .setColorTopRight(0xC000FF00)
                    .setColorBottomLeft(0xD00000FF)
                    .setColorBottomRight(0xD0FFFF00)
                    .build()
            )*/
            .build();

        // add our own elements to the screen
        screen.addElements(elements);

        // get the wrapped player from Guithium
        Player player = Guithium.api().getPlayerManager().get(bukkitPlayer.getUniqueId());

        // open screen to the player
        screen.open(player);
        return true;
    }
}
