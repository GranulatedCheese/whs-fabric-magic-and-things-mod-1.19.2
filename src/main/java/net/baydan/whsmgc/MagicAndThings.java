package net.baydan.whsmgc;

import net.baydan.whsmgc.event.PlayerTickHandler;
import net.baydan.whsmgc.item.ModItems;
import net.baydan.whsmgc.networking.ModPackets;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagicAndThings implements ModInitializer {
	public static final String MOD_ID = "magic-and-things";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModPackets.registerC2SPackets();

		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
		LOGGER.info("Hello Fabric world!");
	}
}
