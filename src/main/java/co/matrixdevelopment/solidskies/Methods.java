package co.matrixdevelopment.solidskies;

import co.matrixdevelopment.solidskies.filehandler.FileManager;
//import co.matrixdevelopment.solidskies.filehandler.FileManager.Files;
//TODO: requires prefix from config not config yet
import co.matrixdevelopment.solidskies.filehandler.Messages;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Methods {

    public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CrazyAuctions");
    private static FileManager fileManager = FileManager.getInstance();

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /*
    public static String getPrefix() {
        return color(Files.CONFIG.getFile().getString("Settings.Prefix"));
    }

    public static String getPrefix(String msg) {
        return color(Files.CONFIG.getFile().getString("Settings.Prefix") + msg);
    }
    */

    public static String removeColor(String msg) {
        return ChatColor.stripColor(msg);
    }

    public static Player getPlayer(String name) {
        return Bukkit.getServer().getPlayer(name);
    }

    @SuppressWarnings("deprecation")
    public static OfflinePlayer getOfflinePlayer(String name) {
        return Bukkit.getServer().getOfflinePlayer(name);
    }

    public static Location getLoc(Player player) {
        return player.getLocation();
    }

    public static void runCMD(Player player, String CMD) {
        player.performCommand(CMD);
    }

    public static boolean isOnline(String name) {
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            if(player.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOnline(String name, CommandSender p) {
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            if(player.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        p.sendMessage(Messages.NOT_ONLINE.getMessageNoPrefix());
        return false;
    }

    public static boolean hasPermission(Player player, String perm) {
        if(!player.hasPermission("solidskies." + perm)) {
            player.sendMessage(Messages.NO_PERMISSION.getMessageNoPrefix());
            return false;
        }
        return true;
    }

    public static boolean hasPermission(CommandSender sender, String perm) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.hasPermission("solidskies." + perm)) {
                player.sendMessage(Messages.NO_PERMISSION.getMessageNoPrefix());
                return false;
            }else {
                return true;
            }
        }else {
            return true;
        }
    }
}
