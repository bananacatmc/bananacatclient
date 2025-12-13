package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;

public class TimeChangerMod extends Mod {
    public TimeChangerMod() {
        super(
                "TimeChanger",
                "Changes the time of the current World visually.",
                Type.Visual
        );

        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Offset", this, 12000, 0));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Speed", this, 50, 1));
    }
}
