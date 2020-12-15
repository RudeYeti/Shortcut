package io.github.rudeyeti.shortcut;

import static io.github.rudeyeti.shortcut.Shortcut.*;

public class Config {

    public static int defaultRadius;

    public static void updateConfig() {
        defaultRadius = config.getInt("default-radius");
    }

    public static boolean validateConfig() {
        updateConfig();

        if (!(config.get("default-radius") instanceof Integer)) {
            logger.warning("The default-radius value in the configuration must be a number.");
            return false;
        } else if (defaultRadius > 100) {
            logger.warning("The default-radius value in the configuration must be 100 or less.");
            return false;
        }
        return true;
    }
}
