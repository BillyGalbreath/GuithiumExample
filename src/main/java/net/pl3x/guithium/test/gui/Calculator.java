package net.pl3x.guithium.test.gui;

import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.element.Button;
import net.pl3x.guithium.api.gui.element.Gradient;

public class Calculator extends Screen {
    public Calculator() {
        super(Key.of("guithium:calc"));

        this.addElement(Gradient.DEFAULT);

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

        addElement(Gradient.builder("calc:display")
            .setPos(col0, row0)
            .setAnchor(0.5F, 0.5F)
            .setSize(86, 20)
            .setColor(0xFF000000)
            .build());

        addElement(button("open_parenthesis", "(", col0, row1));
        addElement(button("close_parenthesis", ")", col1, row1));
        addElement(button("clear", "C", col2, row1));
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
        addElement(button("equals", "=", col3, row5));
    }

    private Button button(String name, String text, int x, int y) {
        return Button.builder("calc:btn_" + name)
            .setPos(x, y)
            .setAnchor(0.5F, 0.5F)
            .setSize(20, 20)
            .setText(text)
            .build();
    }

}
