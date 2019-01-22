package co.matrixdevelopment.solidskies.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.matrixdevelopment.solidskies.world.SSWorld;
import net.md_5.bungee.api.ChatColor;

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
                    World w = wc.createWorld();
                    break;
                case "world_teleport":
                    p.teleport(new Location(Bukkit.getWorld("SolidSkies"), 8, 25, 8));
                    break;
                case "world_delete":
                    File f = Bukkit.getWorld("SolidSkies").getWorldFolder();
                    if (f.exists() && f.isDirectory())
                        deleteWorld(f);
                    break;
                default:
                    p.sendMessage(ChatColor.RED + "This is not a valid command!");
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