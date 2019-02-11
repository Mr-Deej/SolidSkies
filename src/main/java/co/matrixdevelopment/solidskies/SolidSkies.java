package co.matrixdevelopment.solidskies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


import co.matrixdevelopment.solidskies.events.VillagerDeathListener;
import org.apache.commons.lang.StringUtils;
import org.bukkit.plugin.java.JavaPlugin;

import co.matrixdevelopment.solidskies.commands.AdminCommands;
import co.matrixdevelopment.solidskies.filehandler.FileManager;
import co.matrixdevelopment.solidskies.filehandler.Messages;

//TODO: add placeholders for island information, challenge title, etc

public class SolidSkies extends JavaPlugin {

    private static SolidSkies instance;
    public static FileManager fileManager = FileManager.getInstance();

    public static int lastX = 0;
    public static int lastY = 0;

    @Override
    public void onEnable() {
    
        super.onEnable();
        instance = this;

        this.getCommand("sa").setExecutor(new AdminCommands());
        DatabaseManager.getInstance().createIslandTableIfNotExists();
        ResultSet set = DatabaseManager.getInstance().getValuesFromTable("islands");
        try {
            while (set.next()) {
                System.out.println("test");
                String[] currentRow = new String[set.getMetaData().getColumnCount()];
                for (int i = 0; i < set.getMetaData().getColumnCount(); i++) {
                    currentRow[i] = set.getString(i + 1);
                }
                System.out.println(StringUtils.join(Arrays.asList(currentRow), " | "));
            }
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Event Registry
        getServer().getPluginManager().registerEvents(new VillagerDeathListener(), this);

        saveDefaultConfig();
        fileManager.logInfo(true).setup(this);
        Messages.addMissingMessages();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        ResultSet set = DatabaseManager.getInstance().getValuesFromTable("islands");
        try {
            while (set.next()) {
                String[] currentRow = new String[set.getMetaData().getColumnCount()];
                for (int i = 0; i < set.getMetaData().getColumnCount(); i++) {
                    currentRow[i] = set.getString(i + 1);
                }
                System.out.println(StringUtils.join(Arrays.asList(currentRow), " | "));
            }
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static SolidSkies getInstance() {
        return instance;
    }
}