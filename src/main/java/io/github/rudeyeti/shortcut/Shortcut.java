package io.github.rudeyeti.shortcut;

import io.github.rudeyeti.shortcut.commands.ShortcutCommand;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Shortcut extends JavaPlugin {

    public static Plugin plugin;
    public static Configuration configuration;
    public static Logger logger;

    public static int defaultRadius;

    public static void updateConfiguration() {
        defaultRadius = configuration.getInt("default-radius");
    }

    public static boolean validateConfiguration() {
        updateConfiguration();

        if (!(configuration.get("default-radius") instanceof Integer)) {
            logger.warning("The default-radius value in the configuration must be a number.");
            return false;
        } else if (defaultRadius > 100) {
            logger.warning("The default-radius value in the configuration must be 100 or less.");
            return false;
        }
        return true;
    }

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        this.saveDefaultConfig();
        configuration = this.getConfig();
        validateConfiguration();
        updateConfiguration();

        plugin = getPlugin(this.getClass());
        logger = this.getLogger();
        this.getCommand("shortcut").setExecutor(new ShortcutCommand());
    }
}
