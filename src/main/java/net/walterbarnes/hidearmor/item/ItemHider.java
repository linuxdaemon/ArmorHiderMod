package net.walterbarnes.hidearmor.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.walterbarnes.hidearmor.HideArmor;

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
        if (itemStack.getItemDamage() > 0) {
            itemStack.setItemDamage(0);
        } else {
            itemStack.setItemDamage(1);
        }
        return itemStack;
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (itemStack.getItemDamage() > 0) {
            if (entity instanceof EntityPlayer) {
                ((EntityPlayer) entity).addPotionEffect(new PotionEffect(HideArmor.armorPotion.id, 2, 0));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (meta > 0) {
            return showIcon;
        }
        return hideIcon;
    }
}
