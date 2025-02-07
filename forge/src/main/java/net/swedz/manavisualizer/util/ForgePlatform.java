package net.swedz.manavisualizer.util;

import net.minecraftforge.fml.ModList;

public class ForgePlatform implements PlatformHelper {
    @Override
    public boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }
}
