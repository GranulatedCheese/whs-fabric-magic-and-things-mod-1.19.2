package net.baydan.whsmgc.event;

import net.baydan.whsmgc.api.ManaSystemAttributes;
import net.baydan.whsmgc.util.IEntityDataSaver;
import net.baydan.whsmgc.util.ManaData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PlayerTickHandler implements ServerTickEvents.StartTick{
    @Override
    public void onStartTick(MinecraftServer server) {

        // Increases Mana per Tick
        for(ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            // Increases manaAmount every 10 ticks
            if(server.getTicks() % 10 == 0){
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                ManaData.addManaAmount(dataPlayer, ManaSystemAttributes.calculateMaxMana(ManaData.getManaLevel(dataPlayer)));
                player.sendMessage(Text.literal("Added Mana"));
                ManaData.setManaLevel(dataPlayer, 5);
            }

            player.sendMessage(Text.literal("Mana Amount: " + ((IEntityDataSaver) player).getPersistentData().getInt("mana_amount"))
                    .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);


        }


    }
}