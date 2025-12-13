/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.gui.hudeditor.impl.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.gui.Style;
import pro.bananacatmc.gui.hudeditor.HudEditor;
import pro.bananacatmc.gui.hudeditor.impl.HudMod;
import pro.bananacatmc.helpers.render.GLHelper;
import pro.bananacatmc.helpers.render.Helper2D;
import pro.bananacatmc.helpers.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpeedIndicatorHud extends HudMod {

    public SpeedIndicatorHud(String name, int x, int y) {
        super(name, x, y);
        setW(60);
        setH(20);
    }

    @Override
    public void renderMod(int mouseX, int mouseY) {
        GLHelper.startScale(getX(), getY(), getSize());
        if (BananaCat.INSTANCE.modManager.getMod(getName()).isToggled()) {
            if (isModern()) {
                if (isBackground()) {
                    Helper2D.drawRoundedRectangle(getX(), getY(), getW(), getH(), 2, Style.getColor(50).getRGB(), 0);
                }
                BananaCat.INSTANCE.fontHelper.size20.drawString(
                        getMeterPerSecond() + " m/s",
                        getX() + getW() / 2f - (BananaCat.INSTANCE.fontHelper.size20.getStringWidth((getMeterPerSecond() + " m/s")) / 2f),
                        getY() + 6,
                        getColor()
                );
            } else {
                if (isBackground()) {
                    Helper2D.drawRectangle(getX(), getY(), getW(), getH(), Style.getColor(50).getRGB());
                }
                BananaCat.INSTANCE.mc.fontRendererObj.drawString(
                        getMeterPerSecond() + " m/s",
                        getX() + getW() / 2 - (BananaCat.INSTANCE.mc.fontRendererObj.getStringWidth(getMeterPerSecond() + " m/s")) / 2,
                        getY() + 6,
                        getColor()
                );
            }
            super.renderMod(mouseX, mouseY);
        }
        GLHelper.endScale();
    }

    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent.Pre.Text e) {
        GLHelper.startScale(getX(), getY(), getSize());
        if (BananaCat.INSTANCE.modManager.getMod(getName()).isToggled() && !(BananaCat.INSTANCE.mc.currentScreen instanceof HudEditor)) {
            if (isModern()) {
                if (isBackground()) {
                    Helper2D.drawRoundedRectangle(getX(), getY(), getW(), getH(), 2, 0x50000000, 0);
                }
                BananaCat.INSTANCE.fontHelper.size20.drawString(
                        getMeterPerSecond() + " m/s",
                        getX() + getW() / 2f - (BananaCat.INSTANCE.fontHelper.size20.getStringWidth((getMeterPerSecond() + " m/s")) / 2f),
                        getY() + 6,
                        getColor()
                );
            } else {
                if (isBackground()) {
                    Helper2D.drawRectangle(getX(), getY(), getW(), getH(), 0x50000000);
                }
                BananaCat.INSTANCE.mc.fontRendererObj.drawString(
                        getMeterPerSecond() + " m/s",
                        getX() + getW() / 2 - (BananaCat.INSTANCE.mc.fontRendererObj.getStringWidth(getMeterPerSecond() + " m/s")) / 2,
                        getY() + 6,
                        getColor()
                );
            }
        }
        GLHelper.endScale();
    }

    private double getMeterPerSecond() {
        double x = BananaCat.INSTANCE.mc.thePlayer.posX - BananaCat.INSTANCE.mc.thePlayer.prevPosX;
        double z = BananaCat.INSTANCE.mc.thePlayer.posZ - BananaCat.INSTANCE.mc.thePlayer.prevPosZ;
        double speed = net.minecraft.util.MathHelper.sqrt_double(x * x + z * z) * 20;
        return MathHelper.round(speed, 1);
    }

    private int getColor() {
        return BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Font Color").getColor().getRGB();
    }

    private boolean isModern() {
        return BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Mode").getCurrentMode().equalsIgnoreCase("Modern");
    }

    private boolean isBackground() {
        return BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Background").isCheckToggled();
    }
}