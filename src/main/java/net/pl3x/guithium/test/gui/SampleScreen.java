package net.pl3x.guithium.test.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Button;
import net.pl3x.guithium.api.gui.element.Checkbox;
import net.pl3x.guithium.api.gui.element.Circle;
import net.pl3x.guithium.api.gui.element.Element;
import net.pl3x.guithium.api.gui.element.Image;
import net.pl3x.guithium.api.gui.element.Line;
import net.pl3x.guithium.api.gui.element.Radio;
import net.pl3x.guithium.api.gui.element.Slider;
import net.pl3x.guithium.api.gui.element.Text;
import net.pl3x.guithium.test.GuithiumExample;

import java.util.List;

public class SampleScreen extends Screen {
    public SampleScreen() {
        super(Key.of("test:sample_screen"));

        // add one of the provided default backgrounds
        addElement(Screen.TILED_DIRT_BACKGROUND);

        // add our elements to the screen
        addElements(createSampleElements());
    }

    private List<Element> createSampleElements() {
        // populate screen with elements
        return List.of(
            Circle.builder("test:circle")
                .setPos(100, -50)
                .setAnchor(0.5F, 0.5F)
                .setOffset(0.5F, 0.5F)
                .setRadius(50F)
                .setResolution(20)
                .setInnerColor(0xFFFF0000)
                .setOuterColor(0xFF0000FF)
                .build(),
            Circle.builder("test:star")
                .setPos(100, 50)
                .setAnchor(0.5F, 0.5F)
                .setOffset(0.5F, 0.5F)
                .setRadius(100F)
                .setResolution(5)
                .setInnerColor(0xFF6699CC)
                .setOuterColor(0x0)
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
            // mmmm, hayley ^_^
            Image.builder("test:hayley")
                .setSize(120, 150)
                .setPos(0, 20)
                .setTexture(GuithiumExample.HAYLEY)
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
                .setLabel("Click Me")
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
            Text.builder("test:oh_my")
                .setPos(-75, 75)
                .setAnchor(1, 0)
                .setOffset(1, 0)
                .setText(Component.text("Oh My!").color(NamedTextColor.YELLOW))
                .setRotation(40F)
                .setScale(5.0F)
                .build(),
            Slider.builder("test:slider")
                .setLabel("Slider {value}/{max}")
                .setPos(0, 50)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .setValue(0.5D)
                .setMin(0D)
                .setMax(25D)
                .onChange((screen, slider, player, value) -> {
                    // this code will fire when the slider is changed
                    System.out.println("Slider (" + slider.getKey() + ") changed: " + value);
                })
                .build(),
            Checkbox.builder("test:checkbox1")
                .setLabel("One")
                .setPos(0, 75)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, checkbox, player, checked) -> {
                    // this code will fire when the checkbox is toggled
                    System.out.println("Checkbox (" + checkbox.getKey() + ") toggled: " + checked);
                })
                .setTooltip(MiniMessage.miniMessage().deserialize("Just testing out a really long tooltip text for this tiny little checkbox here on the screen so we can see if the text wraps good enough or not."))
                .build(),
            Checkbox.builder("test:checkbox2")
                .setLabel("Two")
                .setPos(0, 100)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, checkbox, player, checked) -> {
                    // this code will fire when the checkbox is toggled
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
                    // this code will fire when the radio button is toggled
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
                    // this code will fire when the radio button is toggled
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
                    // this code will fire when the radio button is toggled
                    System.out.println("Radio (" + radio.getKey() + ") toggled: " + checked);
                })
                .build()
        );
    }
}
