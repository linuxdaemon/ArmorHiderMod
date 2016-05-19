package net.walterbarnes.hidearmor.handler.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.walterbarnes.hidearmor.HideArmor;

@SideOnly(Side.CLIENT)
public class PlayerRenderHandler {
    @SubscribeEvent
    public void onRenderPlayerEvent(RenderPlayerEvent.SetArmorModel event) {
        if (event.entityPlayer != null && event.entityPlayer.isPotionActive(HideArmor.armorPotion.id)) {
            event.result = 0;
        }
    }
}
