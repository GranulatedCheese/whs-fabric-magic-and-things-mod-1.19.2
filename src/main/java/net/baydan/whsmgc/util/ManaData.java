package net.baydan.whsmgc.util;

import net.baydan.whsmgc.networking.ModPackets;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class ManaData {
    // Increase Mana Level
    public static void addManaLevel(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int manaLevel = nbt.getInt("mana_level");

        // Increases mana level only if manaLevel is below 15.
        if(manaLevel + amount >= 15){
            manaLevel = 15;
        } else {
            manaLevel += amount;
        }

        nbt.putInt("mana_level", manaLevel);
        syncManaLevel(manaLevel, (ServerPlayerEntity) player);
    }

    // Increase ManaAmount by 1
    public static void addManaAmount(IEntityDataSaver player, int maxManaAmount) {
        NbtCompound nbt = player.getPersistentData();
        int manaAmount = nbt.getInt("mana_amount");

        //Increases mana by 1 unless it has capped
        if(manaAmount + 1 >= maxManaAmount){
            manaAmount = maxManaAmount;
        } else {
            manaAmount ++;
        }

        nbt.putInt("mana_amount", manaAmount);
        syncManaAmount(manaAmount, (ServerPlayerEntity) player);
    }

    // Decrease ManaAmount by amount
    public static void removeManaAmount(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int manaAmount = nbt.getInt("mana_amount");

        // Decrease manaAmount
        if(manaAmount - amount < 0){
            manaAmount = 0;
        } else {
            manaAmount -= amount;
        }

        nbt.putInt("mana_amount", manaAmount);
        syncManaAmount(manaAmount, (ServerPlayerEntity) player);
    }

    // Getter for manaLevel
    public static int getManaLevel(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getInt("mana_level");
    }

    public static int getManaAmount(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getInt("mana_amount");
    }

    // Setter for ManaLevel

    public static void setManaLevel(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int manaLevel = nbt.getInt("mana_level");
        nbt.putInt("mana_level", amount);
        syncManaLevel(manaLevel, (ServerPlayerEntity) player);
    }

    public static int calculateMaxMana(IEntityDataSaver player, int manaLevel){
        NbtCompound nbt = player.getPersistentData();
        int maxMana = 100;
        // Increases mana by 50% each time
        for(int i = 0; i < manaLevel - 1; i++) {
            maxMana = maxMana + (int)(maxMana * 0.5);
        }
        return maxMana;
    }

    public static void syncManaAmount(int manaAmount, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(manaAmount);
        ServerPlayNetworking.send(player, ModPackets.MANA_SYNC_ID, buffer);
    }

    public static void syncManaLevel(int manaLevel, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(manaLevel);
        ServerPlayNetworking.send(player, ModPackets.MANA_SYNC_ID, buffer);
    }
}
