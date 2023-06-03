package net.baydan.whsmgc.networking;

import net.baydan.whsmgc.networking.packet.KBManaLevelC2SPacket;
import net.baydan.whsmgc.networking.packet.ManaAmountSyncDataS2CPacket;
import net.baydan.whsmgc.networking.packet.KBManaAmountC2SPacket;
import net.baydan.whsmgc.MagicAndThings;
import net.baydan.whsmgc.networking.packet.ManaLevelSyncDataS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static final Identifier REMOVE_MANA_LEVEL_ID = new Identifier(MagicAndThings.MOD_ID, "mana_level");
    public static final Identifier ADD_MANA_LEVEL_ID = new Identifier(MagicAndThings.MOD_ID, "mana_level");
    public static final Identifier MANA_AMOUNT_ID = new Identifier(MagicAndThings.MOD_ID, "mana_amount");

    public static final Identifier MANA_LEVEL_SYNC_ID = new Identifier(MagicAndThings.MOD_ID, "mana_level_sync");
    public static final Identifier MANA_AMOUNT_SYNC_ID = new Identifier(MagicAndThings.MOD_ID, "mana_amount_sync");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(ADD_MANA_LEVEL_ID, KBManaLevelC2SPacket::increaseManaLevel);
        ServerPlayNetworking.registerGlobalReceiver(REMOVE_MANA_LEVEL_ID, KBManaLevelC2SPacket::decreaseManaLevel);

        ServerPlayNetworking.registerGlobalReceiver(MANA_AMOUNT_ID, KBManaAmountC2SPacket::decreaseManaAmount);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(MANA_AMOUNT_SYNC_ID, ManaAmountSyncDataS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(MANA_LEVEL_SYNC_ID, ManaLevelSyncDataS2CPacket::receive);
    }

}
