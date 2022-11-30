package net.pl3x.guithium.test.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Button;
import net.pl3x.guithium.api.gui.element.Checkbox;
import net.pl3x.guithium.api.gui.element.Circle;
import net.pl3x.guithium.api.gui.element.Image;
import net.pl3x.guithium.api.gui.element.Line;
import net.pl3x.guithium.api.gui.element.Radio;
import net.pl3x.guithium.api.gui.element.Slider;
import net.pl3x.guithium.api.gui.element.Text;
import net.pl3x.guithium.api.gui.element.Textbox;
import net.pl3x.guithium.test.GuithiumExample;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SampleScreen extends Screen {
    public SampleScreen() {
        super(Key.of("test:sample_screen"));

        // add one of the provided default backgrounds
        addElement(Screen.GRADIENT_BACKGROUND);

        // add our elements to the screen
        addElements(List.of(
            Text.builder("test:title")
                .setPos(0, 10)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0)
                .setText(Component.text("Guithium").color(NamedTextColor.AQUA).decorate(TextDecoration.ITALIC))
                .setScale(2.5F)
                .build(),
            Line.builder("test:line")
                .setPos(0, 35)
                .setEndPos(0, 35)
                .setEndAnchor(1, 0)
                .setStartColor(0xFFFF0000)
                .setEndColor(0xFF0000FF)
                .setWidth(3F)
                .build(),
            Text.builder("test:oh_my")
                .setPos(80, 25)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0.5F)
                .setText(Component.text("Oh My!").color(NamedTextColor.YELLOW))
                .setRotation(30F)
                .setScale(1.5F)
                .build(),
            Circle.builder("test:star")
                .setPos(55, 165)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0.5F)
                .setRadius(100F)
                .setResolution(5)
                .setInnerColor(0xFF6699CC)
                .setOuterColor(0x0)
                .build(),
            getHayleyOrBanana(),
            Checkbox.builder("test:checkbox1")
                .setLabel("Checkbox 1")
                .setPos(-105, 45)
                .setAnchor(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, checkbox, player, checked) -> {
                    // this code will fire when the checkbox is toggled
                    System.out.println("Checkbox (" + checkbox.getKey() + ") toggled: " + checked);
                })
                .setTooltip(Component.text("Just testing out a really long tooltip text for this tiny little checkbox here on the screen so we can see if the text wraps good enough or not."))
                .build(),
            Checkbox.builder("test:checkbox2")
                .setLabel("Checkbox 2")
                .setPos(-105, 70)
                .setAnchor(0.5F, 0)
                .setSize(100, 20)
                .setSelected(true)
                .onToggled((screen, checkbox, player, checked) -> {
                    // this code will fire when the checkbox is toggled
                    System.out.println("Checkbox (" + checkbox.getKey() + ") toggled: " + checked);
                })
                .setTooltip(MiniMessage.miniMessage().deserialize("<gold><b><i>Minimessage Support!"))
                .build(),
            Checkbox.builder("test:checkbox3")
                .setLabel("Checkbox 3")
                .setPos(-105, 95)
                .setAnchor(0.5F, 0)
                .setSize(100, 20)
                .setSelected(true)
                .onToggled((screen, checkbox, player, checked) -> {
                    // this code will fire when the checkbox is toggled
                    System.out.println("Checkbox (" + checkbox.getKey() + ") toggled: " + checked);
                })
                .build(),
            Slider.builder("test:slider")
                .setLabel("Slider {value}/{max}")
                .setPos(-105, 120)
                .setAnchor(0.5F, 0)
                .setSize(100, 20)
                .setValue(10D)
                .setMin(0D)
                .setMax(25D)
                .setDecimalFormat("0")
                .onChange((screen, slider, player, value) -> {
                    // this code will fire when the slider is changed
                    System.out.println("Slider (" + slider.getKey() + ") changed: " + value);
                })
                .build(),
            Textbox.builder("calc:display")
                .setPos(-105, 145)
                .setAnchor(0.5F, 0)
                .setSize(100, 20)
                .setBordered(true)
                .setValue("Textbox")
                .setTextColorUneditable(0xFFE0E0E0)
                .build(),
            Button.builder("test:button")
                .setLabel("Click Me")
                .setPos(-105, 170)
                .setAnchor(0.5F, 0)
                .setSize(100, 20)
                .onClick((screen, button, player) -> {
                    // this code will fire when the button is clicked
                    System.out.println("onClick fired!");
                    // let's close the screen on the player's client
                    screen.close(player);
                })
                .setTooltip(MiniMessage.miniMessage().deserialize("<green>Clicking <b><blue>this</blue> button\nwill</b> <red>close</red> screen"))
                .build(),
            Radio.builder("test:radio1")
                .setGroup(Key.of("test:radio_group1"))
                .setLabel("Radio Button 1")
                .setPos(5, 45)
                .setAnchor(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, radio, player, checked) -> {
                    // this code will fire when the radio button is toggled
                    System.out.println("Radio (" + radio.getKey() + ") toggled: " + checked);
                })
                .build(),
            Radio.builder("test:radio2")
                .setGroup(Key.of("test:radio_group1"))
                .setLabel("Radio Button 2")
                .setPos(5, 70)
                .setAnchor(0.5F, 0)
                .setSize(100, 20)
                .setSelected(true)
                .onToggled((screen, radio, player, checked) -> {
                    // this code will fire when the radio button is toggled
                    System.out.println("Radio (" + radio.getKey() + ") toggled: " + checked);
                })
                .build(),
            Radio.builder("test:radio3")
                .setGroup(Key.of("test:radio_group1"))
                .setLabel("Radio Button 3")
                .setPos(5, 95)
                .setAnchor(0.5F, 0)
                .setSize(100, 20)
                .onToggled((screen, radio, player, checked) -> {
                    // this code will fire when the radio button is toggled
                    System.out.println("Radio (" + radio.getKey() + ") toggled: " + checked);
                })
                .build()
        ));
    }

    private Image getHayleyOrBanana() {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return Image.builder("test:hayley")
                .setPos(55, 165)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0.5F)
                .setSize(60, 75)
                .setScale(1.2F)
                .setTexture(GuithiumExample.HAYLEY)
                .build();
        } else {
            return Image.builder("test:banana")
                .setPos(55, 165)
                .setAnchor(0.5F, 0)
                .setOffset(0.5F, 0.5F)
                .setSize(75, 75)
                .setScale(1.2F)
                .setTexture(GuithiumExample.BANANA)
                .build();
        }
    }
}
