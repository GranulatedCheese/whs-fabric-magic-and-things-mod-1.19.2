package net.baydan.whsmgc.networking.packet;

import net.baydan.whsmgc.MagicAndThings;
import net.baydan.whsmgc.util.IEntityDataSaver;
import net.baydan.whsmgc.util.ManaData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ManaAmountC2SPacket {
    public static final Identifier ID = new Identifier(MagicAndThings.MOD_ID, "mana_amount");

    public static void send(int amount) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(amount);
        ClientPlayNetworking.send(ID, buf);
    }

    public static void receive(ServerPlayerEntity player, PacketByteBuf buf) {
        int amount = buf.readInt();

        decreaseMana(amount, player);
    }

    public static void decreaseMana(int amount, ServerPlayerEntity player) {
        ManaData.removeManaAmount(((IEntityDataSaver) player), amount);
        player.sendMessage(Text.literal("Removed " + amount + " Mana"));
    }
    public static void decreaseManaAmount(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                        PacketByteBuf buf, PacketSender responseSender) {
        //Everything here occurs only on the server.
        ServerWorld world = player.getWorld();

        // This Occurs when key K is pressed
        // Removes Mana Amount
        ManaData.removeManaAmount(((IEntityDataSaver) player), 50);
        player.sendMessage(Text.literal("Removed 50 Mana"));

        // Sync
        ManaData.syncManaAmount(((IEntityDataSaver) player).getPersistentData().getInt("mana_amount"), player);
    }
}
