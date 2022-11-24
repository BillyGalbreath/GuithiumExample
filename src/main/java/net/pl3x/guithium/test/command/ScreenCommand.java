package net.pl3x.guithium.test.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Button;
import net.pl3x.guithium.api.gui.element.Checkbox;
import net.pl3x.guithium.api.gui.element.Circle;
import net.pl3x.guithium.api.gui.element.Element;
import net.pl3x.guithium.api.gui.element.Image;
import net.pl3x.guithium.api.gui.element.Line;
import net.pl3x.guithium.api.gui.element.Radio;
import net.pl3x.guithium.api.gui.element.Text;
import net.pl3x.guithium.api.player.WrappedPlayer;
import net.pl3x.guithium.test.GuithiumExample;
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
            Circle.builder("test:circle")
                .setPos(100, 0)
                .setAnchor(0.5F, 0.5F)
                .setOffset(0.5F, 0.5F)
                .setRadius(50F)
                .setResolution(20)
                .setInnerColor(0xFFFF0000)
                .setOuterColor(0xFF0000FF)
                .build(),
            // mmmm, hayley ^_^
            Image.builder("test:hayley")
                .setSize(120, 150)
                .setPos(0, 20)
                .setTexture(GuithiumExample.HAYLEY)
                .build(),
            Line.builder("test:line")
                .setPos(0, 50)
                .setAnchor(0, 0)
                .setEndPos(0, 50)
                .setEndAnchor(1, 0)
                .setStartColor(0xFFFF0000)
                .setEndColor(0xFF0000FF)
                .setWidth(5F)
                .build(),
            // some text centered in the screen
            /*Text.builder("test:centered_text")
                .setText(Component.text("Centered Text"))
                .setAnchor(0.5F, 0.5F)
                .setOffset(0.5F, 0.5F)
                .setShadow(false)
                .build(),*/
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
                .build(),
            Checkbox.builder("test:checkbox1")
                .setLabel("One")
                .setPos(0, 50)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, checkbox, player, checked) -> {
                    // this code will fire when the checkbox is clicked
                    System.out.println("Checkbox (" + checkbox.getKey() + ") toggled: " + checked);
                })
                .setTooltip(MiniMessage.miniMessage().deserialize("Just testing out a really long tooltip text for this tiny little checkbox here on the screen so we can see if the text wraps good enough or not."))
                .build(),
            Checkbox.builder("test:checkbox2")
                .setLabel("Two")
                .setPos(0, 75)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, checkbox, player, checked) -> {
                    // this code will fire when the checkbox is clicked
                    System.out.println("Checkbox (" + checkbox.getKey() + ") toggled: " + checked);
                })
                .build(),
            Checkbox.builder("test:checkbox3")
                .setLabel("Three")
                .setPos(0, 100)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, checkbox, player, checked) -> {
                    // this code will fire when the checkbox is clicked
                    System.out.println("Checkbox (" + checkbox.getKey() + ") toggled: " + checked);
                })
                .build(),
            Radio.builder("test:radio1")
                .setGroup(Key.of("test:radio_group1"))
                .setLabel("One")
                .setPos(0, 125)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, radio, player, checked) -> {
                    // this code will fire when the radio button is clicked
                    System.out.println("Radio (" + radio.getKey() + ") toggled: " + checked);
                })
                .build(),
            Radio.builder("test:radio2")
                .setGroup(Key.of("test:radio_group1"))
                .setLabel("Two")
                .setPos(0, 150)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, radio, player, checked) -> {
                    // this code will fire when the radio button is clicked
                    System.out.println("Radio (" + radio.getKey() + ") toggled: " + checked);
                })
                .build(),
            Radio.builder("test:radio3")
                .setGroup(Key.of("test:radio_group1"))
                .setLabel("Three")
                .setPos(0, 175)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, radio, player, checked) -> {
                    // this code will fire when the radio button is clicked
                    System.out.println("Radio (" + radio.getKey() + ") toggled: " + checked);
                })
                .build()
        );

        // create a new screen with custom gradient background
        Screen screen = Screen.builder("test:screen")
            .build();

        // add one of the provided default backgrounds
        screen.addElement(Screen.TILED_DIRT_BACKGROUND);

        // add our elements to the screen
        screen.addElements(elements);

        // get the wrapped player from Guithium
        WrappedPlayer wrappedPlayer = Guithium.api().getPlayerManager().get(bukkitPlayer.getUniqueId());

        // open screen to the player
        screen.open(wrappedPlayer);
        return true;
    }
}
