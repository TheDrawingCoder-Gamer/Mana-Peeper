package net.swedz.manavisualizer.util;

import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatform implements PlatformHelper {
    @Override
    public boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }
}