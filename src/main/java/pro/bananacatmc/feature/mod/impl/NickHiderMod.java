/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;

public class NickHiderMod extends Mod {

    public NickHiderMod() {
        super(
                "NickHider",
                "Hides your nickname in game by replacing it.",
                Type.Visual
        );

        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Nickname", this, "Name", "You", 3));
    }

    public static String replaceNickname(String nick) {
        if(BananaCat.INSTANCE != null) {
            if (BananaCat.INSTANCE.modManager != null) {
                if (BananaCat.INSTANCE.modManager.getMod("NickHider").isToggled()) {
                    return nick.replace(
                            BananaCat.INSTANCE.mc.getSession().getUsername(),
                            BananaCat.INSTANCE.settingManager.getSettingByModAndName("NickHider", "Nickname").getText()
                    );
                }
            }
        }
        return nick;
    }
}
