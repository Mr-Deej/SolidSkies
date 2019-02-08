package co.matrixdevelopment.solidskies.commands;

import java.io.File;
import java.util.HashMap;

//import co.matrixdevelopment.solidskies.filehandler.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.matrixdevelopment.solidskies.DatabaseManager;
import co.matrixdevelopment.solidskies.SolidSkies;
import co.matrixdevelopment.solidskies.island.SSIsland;
import co.matrixdevelopment.solidskies.world.SSWorld;

public class AdminCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length > 0) {
                switch (args[0].toLowerCase()) {
                case "world_create":
                    WorldCreator wc = new WorldCreator("SolidSkies");
                    wc.generator(new SSWorld());
                    wc.createWorld();
                    //p.sendMessage(Messages.WORLD_CREATED.getMessageNoPrefix());
                    //TODO: Fix npe exception with sendMessage
                    break;
                case "world_teleport":
                    p.teleport(new Location(Bukkit.getWorld("SolidSkies"), 8, 25, 8));
                    //p.sendMessage(Messages.WORLD_TELEPORT.getMessageNoPrefix());
                    //TODO: Fix npe exception with sendMessage
                    break;
                case "world_delete":
                    File f = Bukkit.getWorld("SolidSkies").getWorldFolder();
                    if (f.exists() && f.isDirectory())
                        deleteWorld(f);
                    //p.sendMessage(Messages.WORLD_DELETED.getMessageNoPrefix());
                    //TODO: Fix npe exception with sendMessage
                    break;
                case "is_force_create":
                    SSIsland is = new SSIsland(SolidSkies.lastX, SolidSkies.lastY, p);
                    HashMap<String, String> values = new HashMap<String, String>() {
                        private static final long serialVersionUID = 1L;
                        {
                            put("uuid", p.getUniqueId().toString());
                            put("level", Integer.toString(is.getLevel()));
                            put("x", Integer.toString(is.getX()));
                            put("y", Integer.toString(is.getY()));
                        }
                    };
                    DatabaseManager.getInstance().insertValuesIntoTable("islands", values);
                    SolidSkies.lastX += 10;
                    SolidSkies.lastY += 10;
                    //p.sendMessage(Messages.ISLAND_FORCE_CREATED.getMessageNoPrefix());
                    //TODO: Fix npe exception with sendMessage
                    break;
                default:
                    //p.sendMessage(Messages.DEFAULT_MESSAGE.getMessageNoPrefix());
                    //TODO: Fix npe exception with sendMessage
                    break;
                }
            }
        }
        return true;
    }

    public boolean deleteWorld(File path) {
        if (path.exists()) {
            File files[] = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

}