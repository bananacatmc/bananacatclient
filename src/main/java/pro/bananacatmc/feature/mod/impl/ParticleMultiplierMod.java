/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.feature.mod.impl;

import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.mod.Type;
import pro.bananacatmc.feature.setting.Setting;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleMultiplierMod extends Mod {

    public ParticleMultiplierMod() {
        super(
                "ParticleMultiplier",
                "Multiplies or adds Particles by a given amount.",
                Type.Visual
        );

        BananaCat.INSTANCE.settingManager.addSetting(new Setting("Particle Amount", this, 15, 5));
    }

    @SubscribeEvent
    public void onAttack(AttackEntityEvent e) {
        if (e.target instanceof EntityLivingBase) {
            boolean doCriticalDamage =
                    BananaCat.INSTANCE.mc.thePlayer.fallDistance > 0.0F &&
                    !BananaCat.INSTANCE.mc.thePlayer.onGround &&
                    !BananaCat.INSTANCE.mc.thePlayer.isOnLadder() &&
                    !BananaCat.INSTANCE.mc.thePlayer.isInWater() &&
                    !BananaCat.INSTANCE.mc.thePlayer.isPotionActive(Potion.blindness) &&
                            BananaCat.INSTANCE.mc.thePlayer.ridingEntity == null;

            for (int i = 0; i < getAmount(); i++) {
                BananaCat.INSTANCE.mc.thePlayer.onEnchantmentCritical(e.target);
                if (doCriticalDamage) {
                    BananaCat.INSTANCE.mc.thePlayer.onCriticalHit(e.target);
                }
            }

        }
    }

    private float getAmount() {
        return BananaCat.INSTANCE.settingManager.getSettingByModAndName(getName(), "Particle Amount").getCurrentNumber();
    }
}
