package net.pl3x.guithium.test.gui;

import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.Vec2;
import net.pl3x.guithium.api.gui.element.Element;
import net.pl3x.guithium.api.gui.element.Gradient;
import net.pl3x.guithium.api.gui.element.Line;
import net.pl3x.guithium.api.gui.element.Slider;
import net.pl3x.guithium.api.player.WrappedPlayer;

import java.util.List;

public class AnchorsScreen extends Screen {
    private Gradient gradient;
    private Gradient posX;
    private Gradient posY;
    private Gradient anchorX;
    private Gradient anchorY;
    private Line line;

    public AnchorsScreen() {
        super(Key.of("test:anchors_screen"));

        // add one of the provided default backgrounds
        addElement(Screen.TILED_DIRT_BACKGROUND);

        // add our elements to the screen
        addElements(createElements());
    }

    private List<Element> createElements() {
        // populate screen with elements
        return List.of(
            this.gradient = Gradient.builder("test:gradient")
                .setPos(0, 0)
                .setAnchor(0.5F, 0.5F)
                .setOffset(0.5F, 0.5F)
                .setSize(100, 20)
                .setColor(0x80FFFF00)
                .build(),
            this.anchorX = Gradient.builder("test:line_anchor_x").setPos(0, 0).setAnchor(0.5F, 0F).setSize(1, 4000).setColor(0xFF0000FF).build(),
            this.anchorY = Gradient.builder("test:line_anchor_y").setPos(0, 0).setAnchor(0F, 0.5F).setSize(4000, 1).setColor(0xFF0000FF).build(),
            this.posX = Gradient.builder("test:line_pos_x").setPos(0, 0).setAnchor(0.5F, 0F).setSize(1, 4000).setColor(0xFFFF0000).build(),
            this.posY = Gradient.builder("test:line_pos_y").setPos(0, 0).setAnchor(0F, 0.5F).setSize(4000, 1).setColor(0xFFFF0000).build(),
            this.line = Line.builder("test:line").setPos(0, 0).setAnchor(0.5F, 0.5F).setEndPos(0, 0).setEndAnchor(0.5F, 0.5F).setStartColor(0xFF0000FF).setEndColor(0xFFFFFF00).setWidth(1F).build(),
            Slider.builder("test:slider_pos_x").setLabel("\u00a7cPosX {value}").setPos(0, 10).setAnchor(0.5F, 0).setOffset(0.5F, 0).setSize(150, 20).setValue(0D).setMin(-512D).setMax(512D)
                .onChange((screen, slider, player, value) -> {
                    this.gradient.setPos(Vec2.of((float) (double) value, this.gradient.getPos().getY()));
                    updateLines(player);
                }).build(),
            Slider.builder("test:slider_pos_y").setLabel("\u00a7cPosY {value}").setPos(0, 35).setAnchor(0.5F, 0).setOffset(0.5F, 0).setSize(150, 20).setValue(0D).setMin(-512D).setMax(512D)
                .onChange((screen, slider, player, value) -> {
                    this.gradient.setPos(Vec2.of(this.gradient.getPos().getX(), (float) (double) value));
                    updateLines(player);
                }).build(),
            Slider.builder("test:slider_anchor_x").setLabel("\u00a71AnchorX {value}").setPos(0, 60).setAnchor(0.5F, 0).setOffset(0.5F, 0).setSize(150, 20).setValue(0.5D).setMin(0D).setMax(1D)
                .onChange((screen, slider, player, value) -> {
                    this.gradient.setAnchor(Vec2.of((float) (double) value, this.gradient.getAnchor().getY()));
                    updateLines(player);
                }).build(),
            Slider.builder("test:slider_anchor_y").setLabel("\u00a71AnchorY {value}").setPos(0, 85).setAnchor(0.5F, 0).setOffset(0.5F, 0).setSize(150, 20).setValue(0.5D).setMin(0D).setMax(1D)
                .onChange((screen, slider, player, value) -> {
                    this.gradient.setAnchor(Vec2.of(this.gradient.getAnchor().getX(), (float) (double) value));
                    updateLines(player);
                }).build(),
            Slider.builder("test:slider_offset_x").setLabel("OffsetX {value}").setPos(0, 110).setAnchor(0.5F, 0).setOffset(0.5F, 0).setSize(150, 20).setValue(0.5D).setMin(0D).setMax(1D)
                .onChange((screen, slider, player, value) -> {
                    this.gradient.setOffset(Vec2.of((float) (double) value, this.gradient.getOffset().getY()));
                    updateLines(player);
                }).build(),
            Slider.builder("test:slider_offset_y").setLabel("OffsetY {value}").setPos(0, 135).setAnchor(0.5F, 0).setOffset(0.5F, 0).setSize(150, 20).setValue(0.5D).setMin(0D).setMax(1D)
                .onChange((screen, slider, player, value) -> {
                    this.gradient.setOffset(Vec2.of(this.gradient.getOffset().getX(), (float) (double) value));
                    updateLines(player);
                }).build()
        );
    }

    public void updateLines(WrappedPlayer player) {
        this.anchorX.setAnchor(Vec2.of(this.gradient.getAnchor().getX(), this.anchorX.getAnchor().getY()));
        this.anchorX.send(player);

        this.anchorY.setAnchor(Vec2.of(this.anchorY.getAnchor().getX(), this.gradient.getAnchor().getY()));
        this.anchorY.send(player);

        this.posX.setPos(Vec2.of(this.gradient.getPos().getX(), this.posX.getPos().getY()));
        this.posX.setAnchor(Vec2.of(this.gradient.getAnchor().getX(), this.posX.getAnchor().getY()));
        this.posX.send(player);

        this.posY.setPos(Vec2.of(this.posY.getPos().getX(), this.gradient.getPos().getY()));
        this.posY.setAnchor(Vec2.of(this.posY.getAnchor().getX(), this.gradient.getAnchor().getY()));
        this.posY.send(player);

        this.line.setAnchor(this.gradient.getAnchor());
        this.line.setEndPos(this.gradient.getPos());
        this.line.setEndAnchor(this.gradient.getAnchor());
        this.line.send(player);

        this.gradient.send(player);
    }
}
