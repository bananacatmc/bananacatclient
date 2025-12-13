/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiTweaksMod extends Mod {

    public GuiTweaksMod() {
        super(
                "Gui Tweaks",
                "Adds Tweaks to the Gui like blur and transparency.",
                Type.Tweaks
        );

        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Blur Background", this, true));
        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Darken Background", this, true));
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent e) {
        if (BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Blur Background").isCheckToggled()) {
            if (!(e.gui instanceof GuiChat)) {
                try {
                    BananaCat.INSTANCE.mc.entityRenderer.loadShader(
                            new ResourceLocation("shaders/post/blur.json"));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
            if (e.gui == null) {
                if (BananaCat.INSTANCE.mc.entityRenderer.getShaderGroup() != null) {
                    BananaCat.INSTANCE.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                }
            }
        }
    }
}