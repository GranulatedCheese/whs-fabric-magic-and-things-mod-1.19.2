package net.baydan.whsmgc.networking;

import net.baydan.whsmgc.networking.packet.ManaC2SPacket;
import net.baydan.whsmgc.MagicAndThings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static final Identifier MANA_LEVEL_ID = new Identifier(MagicAndThings.MOD_ID, "mana_level");
    public static final Identifier MANA_AMOUNT_ID = new Identifier(MagicAndThings.MOD_ID, "mana_amount");
    public static final Identifier MANA_SYNC_ID = new Identifier(MagicAndThings.MOD_ID, "mana_sync");

    public static void registerC2SPackets() {
        // ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(MANA_LEVEL_ID, ManaC2SPacket::receiveManaLevel);
        ServerPlayNetworking.registerGlobalReceiver(MANA_AMOUNT_ID, ManaC2SPacket::receiveManaAmount);
    }

    public static void registerS2CPackets() {

    }

}
