package com.kljiana.tbys;

import com.kljiana.tbys.config.Config;
import com.kljiana.tbys.network.NetworkHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(TBYS.ModID)
public class TBYS {
    public static final String ModID = "tbys";
    public static final Logger LOGGER = LoggerFactory.getLogger(ModID);
    public TBYS() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.configSpec);
        NetworkHandler.register();
    }
}
