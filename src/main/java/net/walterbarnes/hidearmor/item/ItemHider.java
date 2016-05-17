package net.walterbarnes.hidearmor.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.walterbarnes.hidearmor.HideArmor;
import net.walterbarnes.hidearmor.potion.HideArmorEffect;

public class ItemHider extends Item {
    private IIcon showIcon;
    private IIcon hideIcon;

    public ItemHider() {
        super();
        this.maxStackSize = 1;
        //this.setCreativeTab( Globals.CREATIVE_TAB );
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", HideArmor.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s%s", HideArmor.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
        showIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_show");
        hideIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_hide");
    }

    private String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (player.isPotionActive(HideArmor.potionName.id)) {
            player.removePotionEffect(HideArmor.potionName.id);
        } else {
            player.addPotionEffect(new HideArmorEffect(HideArmor.potionName.id, 1000, 0, false));
        }
        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (meta > 0) {
            return showIcon;
        }
        return hideIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (player.isPotionActive(HideArmor.potionName.id)) {
            stack.setItemDamage(1);
            return showIcon;
        }
        stack.setItemDamage(0);
        return hideIcon;
    }
}
