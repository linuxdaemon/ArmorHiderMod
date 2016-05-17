package net.walterbarnes.hidearmor.handler.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.walterbarnes.hidearmor.HideArmor;

@SideOnly(Side.CLIENT)
public class PlayerRenderHandler {
    @SubscribeEvent
    public void onRenderLivingEntity(RenderPlayerEvent.SetArmorModel event) {
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer && (player = (EntityPlayer) event.entity).isPotionActive(HideArmor.potionName.id)) {
            event.result = 0;
        }
    }
}
