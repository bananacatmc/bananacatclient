package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;

import java.awt.*;

public class HitColorMod extends Mod {
    public HitColorMod() {
        super(
                "Hit Color",
                "Changes the color of damaged entities.",
                Type.Visual
        );

        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Damage Color", this, new Color(255, 0, 0), new Color(255, 0, 0), 0, new float[]{145, 0}));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Alpha", this, 255, 80));
    }
}
