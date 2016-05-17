package net.walterbarnes.hidearmor.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.walterbarnes.hidearmor.item.ItemHider;

public class ModItems {
    public static final Item armorHider = new ItemHider().setUnlocalizedName("armorHider");

    public static void init() {
        GameRegistry.registerItem(armorHider, "armorHider");
    }
}
