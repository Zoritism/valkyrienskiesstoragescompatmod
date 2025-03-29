package com.zoritism.valkyrienskiesstoragescompatmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("valkyrienskiesstoragescompatmod")
public class valkyrienskiesstoragescompatmod {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "valkyrienskiesstoragescompatmod";

    public valkyrienskiesstoragescompatmod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("sophisticatedstorage")) {
            LOGGER.warn("[{}]: SophisticatedStorage is loaded, adding ValkyrienSkies support", MODID);
        }
    }
}