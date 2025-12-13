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
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class BlockinfoHud extends HudMod {

    public BlockinfoHud(String name, int x, int y) {
        super(name, x, y);
        setW(130);
        setH(30);
    }

    @Override
    public void renderMod(int mouseX, int mouseY) {
        GLHelper.startScale(getX(), getY(), getSize());
        if (BananaCat.INSTANCE.modManager.getMod(getName()).isToggled()) {
            if (isModern()) {
                if (isBackground()) {
                    Helper2D.drawRoundedRectangle(getX(), getY(), getW(), getH(), 2, Style.getColor(50).getRGB(), 0);
                }
                setW(BananaCat.INSTANCE.fontHelper.size20.getStringWidth("Grass Block") + 42);
                BananaCat.INSTANCE.fontHelper.size20.drawString("Grass Block", getX() + 35, getY() + 10, getColor());

                ItemStack itemStack = new ItemStack(Blocks.grass);
                renderItem(itemStack);
            } else {
                if (isBackground()) {
                    Helper2D.drawRectangle(getX(), getY(), getW(), getH(), Style.getColor(50).getRGB());
                }
                setW(BananaCat.INSTANCE.mc.fontRendererObj.getStringWidth("Grass Block") + 42);
                BananaCat.INSTANCE.mc.fontRendererObj.drawString("Grass Block", getX() + 35, getY() + 10, getColor());

                ItemStack itemStack = new ItemStack(Blocks.grass);
                renderItem(itemStack);
            }
            super.renderMod(mouseX, mouseY);
        }
        GLHelper.endScale();
    }

    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent.Pre.Text e) {
        IBlockState blockState = getLookingAtBlockState();
        if (blockState == null) {
            return;
        }

        GLHelper.startScale(getX(), getY(), getSize());
        if (BananaCat.INSTANCE.modManager.getMod(getName()).isToggled() && !(BananaCat.INSTANCE.mc.currentScreen instanceof HudEditor)) {

            World world = BananaCat.INSTANCE.mc.theWorld;
            BlockPos blockPos = getLookingAtBlockPos();
            int meta = blockState.getBlock().getDamageValue(world, blockPos);
            int id = Item.itemRegistry.getIDForObject(blockState.getBlock().getItem(world, blockPos));
            ItemStack finalItem = new ItemStack(Item.getItemById(id), 1, meta);
            if (finalItem.getItem() == null)
                finalItem = new ItemStack(blockState.getBlock());
            if (finalItem.getItem() == null) {
                GLHelper.endScale();
                return;
            }

            if (isModern()) {
                setW(BananaCat.INSTANCE.fontHelper.size20.getStringWidth(finalItem.getDisplayName()) + 42);
                if (isBackground()) {
                    Helper2D.drawRoundedRectangle(getX(), getY(), getW(), getH(), 2, 0x50000000, 0);
                }
                BananaCat.INSTANCE.fontHelper.size20.drawString(finalItem.getDisplayName(), getX() + 35, getY() + 10, getColor());
                renderItem(finalItem);
            } else {
                setW(BananaCat.INSTANCE.fontHelper.size20.getStringWidth(finalItem.getDisplayName()) + 42);
                if (isBackground()) {
                    Helper2D.drawRectangle(getX(), getY(), getW(), getH(), 0x50000000);
                }
                BananaCat.INSTANCE.mc.fontRendererObj.drawString(finalItem.getDisplayName(), getX() + 35, getY() + 10, getColor());
                renderItem(finalItem);
            }
        }
        GLHelper.endScale();
    }

    private BlockPos getLookingAtBlockPos() {
        MovingObjectPosition objectMouseOver = BananaCat.INSTANCE.mc.objectMouseOver;
        if (objectMouseOver == null) {
            return null;
        }
        return objectMouseOver.getBlockPos();
    }

    public int getColor() {
        return BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Font Color").getColor().getRGB();
    }

    private boolean isModern() {
        return BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Mode").getCurrentMode().equalsIgnoreCase("Modern");
    }

    private boolean isBackground() {
        return BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Background").isCheckToggled();
    }

    private void renderItem(ItemStack stack) {
        GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();

        GL11.glTranslatef(getX() + 3, getY() + 3, 1);
        GL11.glScalef(1.5f, 1.5f, 1);
        GL11.glTranslatef(-(getX() + 3), -(getY() + 3), -1);
        BananaCat.INSTANCE.mc.getRenderItem().renderItemAndEffectIntoGUI(
                stack, getX() + 3, getY() + 3
        );

        GL11.glPopMatrix();
    }

    private IBlockState getLookingAtBlockState() {
        if (BananaCat.INSTANCE.mc.objectMouseOver != null && BananaCat.INSTANCE.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && BananaCat.INSTANCE.mc.objectMouseOver.getBlockPos() != null) {
            BlockPos blockpos = BananaCat.INSTANCE.mc.objectMouseOver.getBlockPos();
            return BananaCat.INSTANCE.mc.theWorld.getBlockState(blockpos);
        }
        return null;
    }
}