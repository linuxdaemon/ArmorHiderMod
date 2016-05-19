package net.walterbarnes.hidearmor;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.walterbarnes.hidearmor.init.ModItems;
import net.walterbarnes.hidearmor.potion.PotionHideArmor;
import net.walterbarnes.hidearmor.proxy.IProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Mod(modid = HideArmor.MODID, name = HideArmor.MODNAME, version = HideArmor.VERSION)
public class HideArmor {
    public static final String MODID = "hidearmor";
    public static final String MODNAME = "Hidden Armor";
    public static final String VERSION = "@VERSION@";
    public static final String CLIENT_PROXY_CLASS = "net.walterbarnes.hidearmor.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "net.walterbarnes.hidearmor.proxy.ServerProxy";
    @SidedProxy(clientSide = HideArmor.CLIENT_PROXY_CLASS, serverSide = HideArmor.SERVER_PROXY_CLASS)
    public static IProxy proxy;
    public static Potion potionName;
    @Mod.Instance(HideArmor.MODID)
    public HideArmor instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Potion[] potionTypes;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);

            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                    potionTypes = (Potion[]) f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            } catch (Exception e) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(e);
            }
        }
        potionName = new PotionHideArmor(75, false, 0).setIconIndex(0, 0).setPotionName("Hide Armor");
        ModItems.init();
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorHider), Items.leather_chestplate, Blocks.glass);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.initRenderHandlers();
    }
}
