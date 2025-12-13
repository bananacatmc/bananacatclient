/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FullbrightMod extends Mod {

    private float oldGamma;

    public FullbrightMod() {
        super(
                "Fullbright",
                "Changes the Gamma of the game to a given value.",
                Type.Visual
        );

        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Brightness", this, 100, 10));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        oldGamma = BananaCat.INSTANCE.mc.gameSettings.gammaSetting;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        BananaCat.INSTANCE.mc.gameSettings.gammaSetting = oldGamma;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        BananaCat.INSTANCE.mc.gameSettings.gammaSetting =
                BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Brightness").getCurrentNumber();
    }
}
