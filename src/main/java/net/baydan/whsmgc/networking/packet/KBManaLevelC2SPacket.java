package net.baydan.whsmgc.networking.packet;

import net.baydan.whsmgc.util.IEntityDataSaver;
import net.baydan.whsmgc.util.ManaData;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class KBManaLevelC2SPacket {

    public static void increaseManaLevel(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                         PacketByteBuf buf, PacketSender responseSender) {
        //Everything here occurs only on the server.
        ServerWorld world = player.getWorld();

        // This Occurs when key O is pressed
        // Removes Mana Amount
        ManaData.addManaLevel(((IEntityDataSaver) player), 1);
        player.sendMessage(Text.literal("Added 1 Mana Level: " + (((IEntityDataSaver) player).getPersistentData().getInt("mana_level"))));

        // Sync
        ManaData.syncManaAmount(((IEntityDataSaver) player).getPersistentData().getInt("mana_level"), player);
    }

    public static void decreaseManaLevel(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                          PacketByteBuf buf, PacketSender responseSender) {
        //Everything here occurs only on the server.
        ServerWorld world = player.getWorld();

        // This Occurs when key P is pressed
        // Removes Mana Amount
        ManaData.addManaLevel(((IEntityDataSaver) player), -1);
        player.sendMessage(Text.literal("Removed 1 Mana Level: " + (((IEntityDataSaver) player).getPersistentData().getInt("mana_level"))));

        // Sync
        ManaData.syncManaAmount(((IEntityDataSaver) player).getPersistentData().getInt("mana_level"), player);
    }
}
