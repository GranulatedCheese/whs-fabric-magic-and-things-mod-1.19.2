package net.baydan.whsmgc.util;

import net.minecraft.nbt.NbtCompound;

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
        // Sync data
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
        // Sync data
    }

    // Decrease ManaAmount by amount
    public static int removeManaAmount(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int manaAmount = nbt.getInt("mana_amount");

        // Decrease manaAmount
        if(manaAmount - amount < 0){
            manaAmount = 0;
        } else {
            manaAmount --;
        }

        nbt.putInt("mana_amount", manaAmount);
        // Sync data
        return manaAmount;
    }

    // Getter for manaLevel
    public static int getManaLevel(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getInt("mana_level");
    }

    // Setter for ManaLevel

    public static void setManaLevel(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt("mana_level", amount);
    }
}
