package net.baydan.whsmgc.networking.packet;

import net.baydan.whsmgc.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class ManaSyncDataS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        ((IEntityDataSaver) client.player).getPersistentData().putInt("mana_amount", buf.readInt());
        ((IEntityDataSaver) client.player).getPersistentData().putInt("mana_level", buf.readInt());
    }
}
