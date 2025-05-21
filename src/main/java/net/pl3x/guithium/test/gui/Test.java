package net.pl3x.guithium.test.gui;

import java.util.List;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Button;
import net.pl3x.guithium.api.gui.element.Checkbox;
import net.pl3x.guithium.api.gui.element.Radio;
import net.pl3x.guithium.api.gui.element.Slider;
import net.pl3x.guithium.api.key.Key;

public class Test extends Screen {
    public Test() {
        super("test:screen", true);

        Key radioGroup1 = Key.of("radioGroup1");

        addElements(List.of(
                new Button("test:btn")
                        .setLabel("button")
                        .setSize(100, 20)
                        .setPos(10, 10),

                new Checkbox("test:checkbox1")
                        .setLabel("checkbox 1")
                        .setSize(100, 20)
                        .setPos(10, 30),
                new Checkbox("test:checkbox2")
                        .setLabel("checkbox 2")
                        .setSize(100, 20)
                        .setPos(10, 50),
                new Checkbox("test:checkbox3")
                        .setLabel("checkbox 3")
                        .setSize(100, 20)
                        .setPos(10, 70),

                new Radio("test:radio1")
                        .setLabel("radio 1")
                        .setSize(100, 20)
                        .setPos(10, 90)
                        .setGroup(radioGroup1),
                new Radio("test:radio2")
                        .setLabel("radio 2")
                        .setSize(100, 20)
                        .setPos(10, 110)
                        .setGroup(radioGroup1),
                new Radio("test:radio3")
                        .setLabel("radio 3")
                        .setSize(100, 20)
                        .setPos(10, 130)
                        .setGroup(radioGroup1),

                new Slider("test:slider")
                        .setLabel("slider {val} ({min}-{max})")
                        .setSize(100, 20)
                        .setPos(10, 150)
        ));
    }
}
