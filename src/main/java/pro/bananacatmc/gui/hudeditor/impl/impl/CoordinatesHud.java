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
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CoordinatesHud extends HudMod {

    public CoordinatesHud(String name, int x, int y) {
        super(name, x, y);
        setW(120);
        setH(60);
    }

    @Override
    public void renderMod(int mouseX, int mouseY) {
        GLHelper.startScale(getX(), getY(), getSize());
        if (BananaCat.INSTANCE.modManager.getMod(getName()).isToggled()) {
            if (isModern()) {
                if (isBackground()) {
                    Helper2D.drawRoundedRectangle(getX(), getY(), getW(), getH(), 2, Style.getColor(50).getRGB(), 0);
                }
                BananaCat.INSTANCE.fontHelper.size20.drawString("X: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posX, 1), getX() + 5, getY() + 5, getColor());
                BananaCat.INSTANCE.fontHelper.size20.drawString("Y: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posY, 1), getX() + 5, getY() + 5 + 14, getColor());
                BananaCat.INSTANCE.fontHelper.size20.drawString("Z: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posZ, 1), getX() + 5, getY() + 5 + 28, getColor());
                BananaCat.INSTANCE.fontHelper.size20.drawString(isBiome() ? "Biome: " + getBiomeName() : "", getX() + 5, getY() + 5 + 42, getColor());
            } else {
                if (isBackground()) {
                    Helper2D.drawRectangle(getX(), getY(), getW(), getH(), Style.getColor(50).getRGB());
                }
                BananaCat.INSTANCE.mc.fontRendererObj.drawString("X: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posX, 1), getX() + 5, getY() + 5, getColor());
                BananaCat.INSTANCE.mc.fontRendererObj.drawString("Y: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posY, 1), getX() + 5, getY() + 5 + 14, getColor());
                BananaCat.INSTANCE.mc.fontRendererObj.drawString("Z: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posZ, 1), getX() + 5, getY() + 5 + 28, getColor());
                BananaCat.INSTANCE.mc.fontRendererObj.drawString(isBiome() ? "Biome: " + getBiomeName() : "", getX() + 5, getY() + 5 + 42, getColor());
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
                BananaCat.INSTANCE.fontHelper.size20.drawString("X: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posX, 1), getX() + 5, getY() + 5, getColor());
                BananaCat.INSTANCE.fontHelper.size20.drawString("Y: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posY, 1), getX() + 5, getY() + 5 + 14, getColor());
                BananaCat.INSTANCE.fontHelper.size20.drawString("Z: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posZ, 1), getX() + 5, getY() + 5 + 28, getColor());
                BananaCat.INSTANCE.fontHelper.size20.drawString(isBiome() ? "Biome: " + getBiomeName() : "", getX() + 5, getY() + 5 + 42, getColor());
            } else {
                if (isBackground()) {
                    Helper2D.drawRectangle(getX(), getY(), getW(), getH(), 0x50000000);
                }
                BananaCat.INSTANCE.mc.fontRendererObj.drawString("X: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posX, 1), getX() + 5, getY() + 5, getColor());
                BananaCat.INSTANCE.mc.fontRendererObj.drawString("Y: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posY, 1), getX() + 5, getY() + 5 + 14, getColor());
                BananaCat.INSTANCE.mc.fontRendererObj.drawString("Z: " + MathHelper.round(BananaCat.INSTANCE.mc.thePlayer.posZ, 1), getX() + 5, getY() + 5 + 28, getColor());
                BananaCat.INSTANCE.mc.fontRendererObj.drawString(isBiome() ? "Biome: " + getBiomeName() : "", getX() + 5, getY() + 5 + 42, getColor());
            }
        }
        GLHelper.endScale();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (isBiome()) {
            setW(120);
            setH(60);
        } else {
            setW(70);
            setH(45);
        }
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

    private boolean isBiome() {
        return BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Biome").isCheckToggled();
    }

    private String getBiomeName() {
        return BananaCat.INSTANCE.mc.theWorld.getBiomeGenForCoords(new BlockPos(BananaCat.INSTANCE.mc.thePlayer.posX, BananaCat.INSTANCE.mc.thePlayer.posY, BananaCat.INSTANCE.mc.thePlayer.posZ)).biomeName;
    }
}
