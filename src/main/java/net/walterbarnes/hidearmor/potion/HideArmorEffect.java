package net.walterbarnes.hidearmor.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class HideArmorEffect extends PotionEffect {

    public HideArmorEffect(int id, int duration, int amplifier, boolean ambient) {
        super(id, duration, amplifier, ambient);
    }

    @Override
    public boolean onUpdate(EntityLivingBase p_76455_1_) {
        return true;
    }
}
