package net.baydan.whsmgc.client;

import net.baydan.whsmgc.networking.ModPackets;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ClientPlayConnectionJoin implements ClientPlayConnectionEvents.Join {


    @Override
    public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
        ClientPlayNetworking.send(ModPackets.MANA_LEVEL_SYNC_ID, PacketByteBufs.create());
        ClientPlayNetworking.send(ModPackets.MANA_AMOUNT_SYNC_ID, PacketByteBufs.create());
    }
}
