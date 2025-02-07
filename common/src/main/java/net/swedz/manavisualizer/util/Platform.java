package net.swedz.manavisualizer.util;

import java.util.ServiceLoader;

public class Platform {
    public static final PlatformHelper PLATFORM = findService(PlatformHelper.class);

    public static <T> T findService(Class<T> clazz) {
        return ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}
