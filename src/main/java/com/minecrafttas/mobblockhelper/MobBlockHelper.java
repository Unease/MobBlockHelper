package com.minecrafttas.mobblockhelper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class MobBlockHelper implements ModInitializer {

	public static Logger LOGGER = LogManager.getLogger("MobBlockHelper");
	public static boolean isTASmodLoaded = FabricLoader.getInstance().isModLoaded("tasmod");
	@Override
	public void onInitialize() {
		
		LOGGER.info("Initializing MobBlockHelper...");
		
		if (isTASmodLoaded) {
			LOGGER.info("TASmod is detected! The rest of the mod will function!");
		}
	}
}
