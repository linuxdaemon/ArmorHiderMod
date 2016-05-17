package net.walterbarnes.hidearmor.potion;

import net.minecraft.potion.Potion;

public class PotionHideArmor extends Potion {
    public PotionHideArmor(int id, boolean isBadEffect, int liquidColor) {
        super(id, isBadEffect, liquidColor);
    }

    public Potion setIconIndex(int par1, int par2) {
        super.setIconIndex(par1, par2);
        return this;
    }
}
