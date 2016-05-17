package net.walterbarnes.hidearmor.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import net.walterbarnes.hidearmor.handler.client.PlayerRenderHandler;

public class ClientProxy extends CommonProxy {
    @Override
    public void initRenderHandlers() {
        System.out.println("initRenderHandlers");
        PlayerRenderHandler playerRenderHandler = new PlayerRenderHandler();
        MinecraftForge.EVENT_BUS.register(playerRenderHandler);
        FMLCommonHandler.instance().bus().register(playerRenderHandler);
    }
}
