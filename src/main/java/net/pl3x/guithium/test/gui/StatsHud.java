package net.pl3x.guithium.test.gui;

import net.kyori.adventure.text.Component;
import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.Vec4;
import net.pl3x.guithium.api.gui.element.Gradient;
import net.pl3x.guithium.api.gui.element.Image;
import net.pl3x.guithium.api.gui.element.Text;
import net.pl3x.guithium.api.gui.texture.Texture;
import net.pl3x.guithium.api.player.WrappedPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class StatsHud extends Screen {
    public static final Texture ICONS = Texture.of("test:icons_tex", "minecraft:textures/gui/icons.png");
    public static final Texture HUD = Texture.of("test:stats_hud", "https://i.imgur.com/FzJC3zA.png");

    private final WrappedPlayer player;

    private final Text healthText;
    private final Image healthMask;
    private final Gradient healthColor;

    public StatsHud(WrappedPlayer player) {
        super(Key.of("test:stats_hud"), Type.HUD);

        this.player = player;

        addElements(List.of(
            Image.builder("test:stats_hud")
                .setPos(15, 10)
                .setSize(100, 14)
                .setTexture(HUD)
                .setUV(Vec4.of(0, 0, 100 / 128F, 14 / 128F))
                .build(),
            this.healthColor = Gradient.builder("test:stats_heart_color")
                .setPos(18, 13)
                .setSize(94, 8)
                .setColor(0xFFFF0000)
                .build(),
            this.healthMask = Image.builder("test:stats_hud_mask")
                .setPos(15, 10)
                .setSize(100, 14)
                .setTexture(HUD)
                .setUV(Vec4.of(0, 14 / 128F, 100 / 128F, 28 / 128F))
                .build(),
            Image.builder("test:stats_heart_bg")
                .setPos(2, 12)
                .setSize(10, 10)
                .setTexture(ICONS)
                .setUV(Vec4.of(34 / 256F, 0, 43 / 256F, 9 / 256F))
                .build(),
            Image.builder("test:stats_heart_fg")
                .setPos(2, 12)
                .setSize(10, 10)
                .setTexture(ICONS)
                .setUV(Vec4.of(52 / 256F, 0, 61 / 256F, 9 / 256F))
                .build(),
            this.healthText = Text.builder("test:stat_health")
                .setPos(21, 15)
                .setScale(0.5F)
                .build()
        ));

        update();
    }

    public void update() {
        Player player = this.player.unwrap();
        double health = player.getHealth();
        double maxHealth = player.getMaxHealth();

        this.healthColor.setSize((float) (94 * (health / maxHealth)), 8);
        this.healthColor.send(this.player);

        this.healthText.setText(Component.text(health + "/" + maxHealth));
        this.healthText.send(this.player);
    }
}
