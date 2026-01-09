/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;

public class ArmorMod extends Mod {

    public ArmorMod() {
        super(
                "Armor Status",
                "Displays your Armor on the HUD.",
                Type.Hud
        );

        String[] mode = {"Modern", "Legacy"};
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Mode", this, "Modern", 0, mode));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Background", this, true));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("No Armor Background", this, true));
    }
}
