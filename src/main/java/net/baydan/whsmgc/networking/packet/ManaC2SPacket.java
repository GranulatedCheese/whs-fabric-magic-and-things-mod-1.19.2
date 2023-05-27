package net.baydan.whsmgc.networking.packet;

import net.baydan.whsmgc.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ManaC2SPacket {
    public static void receiveManaLevel(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        //Everything here occurs only on the server.
        ServerWorld world = player.getWorld();

        // Outputs ManaAmount
        /* Testing Debug
        player.sendMessage(Text.literal("Mana Amount: " + ((IEntityDataSaver) player).getPersistentData().getInt("mana_amount"))
                .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);

         */
    }

    public static void receiveManaAmount(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                        PacketByteBuf buf, PacketSender responseSender) {
        //Everything here occurs only on the server.
        ServerWorld world = player.getWorld();

        // Outputs ManaLevel
        player.sendMessage(Text.literal("Mana Amount: " + ((IEntityDataSaver) player).getPersistentData().getInt("mana_amount"))
                .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
    }
}
