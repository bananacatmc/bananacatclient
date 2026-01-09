package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;

public class ScoreboardMod extends Mod {

    public ScoreboardMod() {
        super(
                "Scoreboard",
                "Adds Tweaks to the Scoreboard",
                Type.Tweaks
        );

        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Background", this, true));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Remove Red Numbers", this, false));
    }
}
