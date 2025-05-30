package net.pl3x.guithium.test.gui;

import java.text.DecimalFormat;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.gui.Vec4;
import net.pl3x.guithium.api.gui.element.Gradient;
import net.pl3x.guithium.api.gui.element.Image;
import net.pl3x.guithium.api.gui.element.Text;
import net.pl3x.guithium.api.gui.texture.Texture;
import net.pl3x.guithium.api.key.Key;
import net.pl3x.guithium.api.player.WrappedPlayer;
import org.bukkit.entity.Player;

public class StatsHud extends Screen {
    public static final Texture HEART_CONTAINER = Texture.of("test:icons_tex", "minecraft:textures/gui/sprites/hud/heart/container.png");
    public static final Texture HEART_FULL = Texture.of("test:icons_tex", "minecraft:textures/gui/sprites/hud/heart/full.png");
    public static final Texture PROGRESSBAR = Texture.of("test:stats_hud", "https://pl3x.net/progressbar.png");

    private final WrappedPlayer player;

    private final Text healthText;
    private final Gradient healthColor;

    private final DecimalFormat df = new DecimalFormat("0.#");

    public StatsHud(WrappedPlayer player) {
        super(Key.of("test:stats_hud"), true);

        this.player = player;

        addElements(List.of(
                new Image("test:stats_hud")
                        .setPos(15, 10)
                        .setSize(100, 14)
                        .setTexture(PROGRESSBAR)
                        .setUV(Vec4.of(0, 0, 1F, 14 / 28F)),
                this.healthColor = new Gradient("test:stats_heart_color")
                        .setPos(18, 13)
                        .setSize(94, 8)
                        .setColorAll(0xFFFF0000),
                new Image("test:stats_hud_mask")
                        .setPos(15, 10)
                        .setSize(100, 14)
                        .setTexture(PROGRESSBAR)
                        .setUV(Vec4.of(0, 14 / 28F, 1F, 1F)),
                new Image("test:stats_heart_bg")
                        .setPos(3.5F, 12.5F)
                        .setSize(9, 9)
                        .setTexture(HEART_CONTAINER),
                new Image("test:stats_heart_fg")
                        .setPos(3.5F, 12.5F)
                        .setSize(9, 9)
                        .setTexture(HEART_FULL),
                this.healthText = new Text("test:stat_health")
                        .setPos(15, 14)
                        .setScale(0.5F)
        ));

        update();
    }

    public void update() {
        Player player = this.player.unwrap();
        double health = player.getHealth();
        double maxHealth = getMaxHealth(player);
        float percent = (float) (health / maxHealth);

        this.healthColor.setSize(94 * percent, 8);
        if (percent > 0.5F) {
            this.healthColor.setColorAll(0xFFFF0000);
        } else if (percent > 0.2F) {
            this.healthColor.setColorAll(0xFFB80000);
        } else {
            this.healthColor.setColorAll(0xFF800000);
        }
        this.healthColor.send(this.player);

        this.healthText.setText(Component.text(this.df.format(health) + "/" + this.df.format(maxHealth)));
        this.healthText.send(this.player);
    }

    @SuppressWarnings("deprecation")
    private double getMaxHealth(Player player) {
        return player.getMaxHealth();
    }
}
