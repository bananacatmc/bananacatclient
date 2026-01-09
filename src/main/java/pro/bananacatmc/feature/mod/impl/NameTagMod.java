package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;

import java.awt.*;

public class NameTagMod extends Mod {

    public NameTagMod() {
        super(
                "NameTag",
                "Adds tweaks to NameTags.",
                Type.Tweaks
        );

        BananaCat.INSTANCE.settingManager.addSetting(new Setting("NameTag in 3rd Person", this, true));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Opacity", this, 255, 64));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Size", this, 3, 1));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Y Position", this, 5, 2.5f));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Disable Player NameTags", this, false));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Font Color", this, new Color(255, 255, 255), new Color(255, 0, 0), 0, new float[]{0, 0}));
    }
}
