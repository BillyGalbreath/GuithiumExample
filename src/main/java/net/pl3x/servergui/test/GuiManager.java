package net.pl3x.servergui.test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiManager {
    private final Map<UUID, CoordsHUD> huds = new HashMap<>();

    public CoordsHUD getHud(UUID uuid) {
        return this.huds.computeIfAbsent(uuid, k -> new CoordsHUD());
    }

    public void removeHud(UUID uuid) {
        this.huds.remove(uuid);
    }
}
