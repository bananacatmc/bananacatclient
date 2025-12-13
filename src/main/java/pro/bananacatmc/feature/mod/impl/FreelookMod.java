/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class FreelookMod extends Mod {

    public static boolean cameraToggled = false;
    public static float cameraYaw;
    public static float cameraPitch;

    public FreelookMod() {
        super(
                "Freelook",
                "Allows you to see a 360 view around your Player.",
                Type.Mechanic
        );

        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Keybinding", this, Keyboard.KEY_R));
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e) {
        if(Keyboard.isKeyDown(getKey()) && !cameraToggled){
            cameraYaw = BananaCat.INSTANCE.mc.thePlayer.rotationYaw + 180;
            cameraPitch = BananaCat.INSTANCE.mc.thePlayer.rotationPitch;
            cameraToggled = true;
            BananaCat.INSTANCE.mc.gameSettings.thirdPersonView = 1;
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e){
        if(!Keyboard.isKeyDown(getKey()) && cameraToggled) {
            cameraToggled = false;
            BananaCat.INSTANCE.mc.gameSettings.thirdPersonView = 0;
        }
    }

    @SubscribeEvent
    public void cameraSetup(EntityViewRenderEvent.CameraSetup e) {
        if (cameraToggled) {
            e.yaw = cameraYaw;
            e.pitch = cameraPitch;
        }
    }

    private int getKey(){
        return BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Keybinding").getKey();
    }
}
