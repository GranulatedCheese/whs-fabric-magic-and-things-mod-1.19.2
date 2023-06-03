package net.baydan.whsmgc;

import net.baydan.whsmgc.client.ClientPlayConnectionJoin;
import net.baydan.whsmgc.client.gui.ManaHudOverlay;
import net.baydan.whsmgc.event.KeyInputHandler;
import net.baydan.whsmgc.networking.ModPackets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class MagicAndThingsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        KeyInputHandler.register();

        ModPackets.registerS2CPackets();

        //Events
        //HudRenderCallback.EVENT.register(new ManaHudOverlay());
    }
}
