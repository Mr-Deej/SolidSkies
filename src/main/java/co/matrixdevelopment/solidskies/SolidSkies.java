package co.matrixdevelopment.solidskies;

import org.bukkit.plugin.java.JavaPlugin;

import co.matrixdevelopment.solidskies.commands.AdminCommands;

public class SolidSkies extends JavaPlugin {

    private static SolidSkies instance;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        this.getCommand("sa").setExecutor(new AdminCommands());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static SolidSkies getInstance() {
        return instance;
    }
}