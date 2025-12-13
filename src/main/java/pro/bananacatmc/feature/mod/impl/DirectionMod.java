package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;

import java.awt.*;

public class DirectionMod extends Mod {

    public DirectionMod() {
        super(
                "Direction",
                "Shows you the direction you are facing on the HUD.",
                Type.Hud
        );

        String[] mode = {"Modern", "Legacy"};
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Mode", this, "Modern", 0, mode));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Background", this, true));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Font Color", this, new Color(255, 255, 255), new Color(255, 0, 0), 0, new float[]{0, 0}));
    }
}
