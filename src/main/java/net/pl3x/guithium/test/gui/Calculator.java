package net.pl3x.guithium.test.gui;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Button;
import net.pl3x.guithium.api.gui.element.Textbox;

public class Calculator extends Screen {
    private static final ScriptEngineManager SEM = new ScriptEngineManager();
    private static final ScriptEngine SE = SEM.getEngineByName("JavaScript");

    private final Textbox display;

    public Calculator() {
        super(Key.of("guithium:calc"));

        // add one of the provided default backgrounds
        this.addElement(Screen.GRADIENT_BACKGROUND);

        // prepare button locations
        int row0 = -77;
        int row1 = -55;
        int row2 = -33;
        int row3 = -11;
        int row4 = 11;
        int row5 = 33;

        int col0 = -43;
        int col1 = -21;
        int col2 = 1;
        int col3 = 23;

        // the display output
        this.display = Textbox.builder("calc:display")
                .setPos(col0, row0)
                .setAnchor(0.5F, 0.5F)
                .setSize(86, 20)
                .setBordered(true)
                .setCanLoseFocus(false)
                .setMaxLength(32)
                .setEditable(false)
                .setTextColorUneditable(0xFFE0E0E0)
                .build();
        addElement(this.display);

        // add all the rows of buttons
        addElement(button("open_parenthesis", "(", col0, row1));
        addElement(button("close_parenthesis", ")", col1, row1));
        addElement(button("clear", "C", col2, row1, (screen, button, player) -> {
            // clear out the display's value
            this.display.setValue("");
            this.display.send(player);
        }));
        addElement(button("multiply", "*", col3, row1));

        addElement(button("seven", "7", col0, row2));
        addElement(button("eight", "8", col1, row2));
        addElement(button("nine", "9", col2, row2));
        addElement(button("divide", "/", col3, row2));

        addElement(button("four", "4", col0, row3));
        addElement(button("five", "5", col1, row3));
        addElement(button("six", "6", col2, row3));
        addElement(button("add", "+", col3, row3));

        addElement(button("one", "1", col0, row4));
        addElement(button("two", "2", col1, row4));
        addElement(button("three", "3", col2, row4));
        addElement(button("subtract", "-", col3, row4));

        addElement(button("zero", "0", col0, row5));
        addElement(button("dot", ".", col1, row5));
        addElement(button("equals", "=", col3, row5, (screen, button, player) -> {
            // calculate the equation on the display
            try {
                String value = this.display.getValue();
                String answer = String.valueOf(SE.eval(value));
                if (answer.endsWith(".0")) {
                    answer = answer.substring(0, answer.length() - 2);
                }
                this.display.setValue(answer);
            } catch (ScriptException e) {
                this.display.setValue("Error");
            }
            this.display.send(player);
        }));
    }

    private Button button(String name, String text, int x, int y) {
        // create a new button with specified properties and the standard onClick
        return button(name, text, x, y, (screen, button, player) -> {
            // add this button's text label to the equation in the display
            String value = this.display.getValue() == null ? "" : this.display.getValue();
            String buttonLabel = button.getLabel() == null ? "" : PlainTextComponentSerializer.plainText().serialize(button.getLabel());
            this.display.setValue(value + buttonLabel);
            this.display.send(player);
        });
    }

    private Button button(String name, String text, int x, int y, Button.OnClick onClick) {
        // create a new button with specified properties
        return Button.builder("calc:btn_" + name)
                .setPos(x, y)
                .setAnchor(0.5F, 0.5F)
                .setSize(20, 20)
                .setLabel(text)
                .onClick(onClick)
                .build();
    }
}
