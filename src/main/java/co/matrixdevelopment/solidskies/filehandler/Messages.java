package co.matrixdevelopment.solidskies.filehandler;

import org.bukkit.configuration.file.FileConfiguration;

import co.matrixdevelopment.solidskies.filehandler.FileManager.Files;
import co.matrixdevelopment.solidskies.Methods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum Messages {

    //TODO add placeholders for island information, challenge title, etc

    INVALID_COMMAND("Invalid-Command","&cPlease check the solid skies help command&7."),
    PLAYERS_ONLY("Players-Only", "&cOnly players can use this command&7."),
    RELOAD("Reload", "&7You have just reloaded the Crazy Auctions Files&7."),
    NO_PERMISSION("No-Permission", "&cYou do not have permission to use that command&7!"),
    NOT_ONLINE("Not-Online", "&cThat player is not online at this time&7."),
    NOT_A_PLAYER("Not-A-Player", "&c%Arg% is not a Player&7."),
    SOMEONE_COMPLETED_CHALLENGE("Someone-Completed-Challenge", "&a%Player%&7 has completed &a%challenge%&7."),
    PARTY_FULL("Party-Full", "&7Your party if currently full."),
    ADDED_PLAYER_TO_PARTY("Added-Player-To-Team", "&7You have just added &a%user%&7 to the team."),
    REMOVED_PLAYER_FROM_PARTY("Removed-Player-From-Team", "&7You have just added &a%user%&7 to the team&7."),
    ISLAND_LOCKED("Island-Closed", "&7The island is now &cclosed&7."),
    ISLAND_OPENED("Island-Opened", "&7The island isn now &aopen&7."),
    ISLAND_FORCE_DELETED("Island-Force-Deleted", "&7This island has been deleted by an Admin&7."),
    ISLAND_FORCE_CREATED("Island-Force-Created", "&7You have forcefully created an island."),
    ISLAND_CREATED("Island-Created", "&7You have created your island."),
    ISLAND_DELETED("Island-Deleted", "&7You have deleted your island."),
    WORLD_CREATED("World-Created", "&7You have created the SolidSkies island world."),
    WORLD_DELETED("World-Deleted", "&7You have deleted the SolidSkies island world"),
    WORLD_TELEPORT("World-Teleport", "&7You have been teleported to the SolidSkies island world at &ex:8 y:25 z:8"),
    SolidSkies_HELP("SolidSkies-Help", "&7/ss help"),
    HELP("Help-Menu", Arrays.asList(
            "&e-- &6Solid Skies Help &e--",
            "&9/ss Help - &eOpens this help menu.",
            "&9/sa world_create - &eCreates the SolidSkies island world.",
            "&9/sa world_delete - &eDeletes the SolidSkies island world.",
            "&9/sa is_force_create - &eCreates an Island forcefully."));

    private String path;
    private String defaultMessage;
    private List<String> defaultListMessage;
    private static FileManager fileManager = FileManager.getInstance();

    private Messages(String path, String defaultMessage) {
        this.path = path;
        this.defaultMessage = defaultMessage;
    }

    private Messages(String path, List<String> defaultListMessage) {
        this.path = path;
        this.defaultListMessage = defaultListMessage;
    }

    //TODO: these are noted because the prefix statement causes an NPE requesting help
    /*public String getMessage() {
        if(isList()) {
            if(exists()) {
                return Methods.color(convertList(Files.MESSAGES.getFile().getStringList("Messages." + path)));
            } else {
                return Methods.color(convertList(getDefaultListMessage()));
            }
        }  else {
            if(exists()) {
                return Methods.getPrefix(Files.MESSAGES.getFile().getString("Messages." + path));
            } else {
                return Methods.getPrefix(getDefaultMessage());
            }
        }
    }

    public String getMessage(HashMap<String, String> placeholders) {
        String message;
        if(isList()) {
            if(exists()) {
                message = Methods.color(convertList(Files.MESSAGES.getFile().getStringList("Messages." + path), placeholders));
            } else {
                message = Methods.color(convertList(getDefaultListMessage(), placeholders));
            }
        } else {
            if(exists()) {
                message = Methods.getPrefix(Files.MESSAGES.getFile().getString("Messages." + path));
            } else {
                message = Methods.getPrefix(getDefaultMessage());
            }
            for(String ph : placeholders.keySet()) {
                if(message.contains(ph)) {
                    message = message.replaceAll(ph, placeholders.get(ph)).replaceAll(ph, placeholders.get(ph).toLowerCase());
                }
            }
        }
        return message;
    }*/

    public String getMessageNoPrefix() {
        if(isList()) {
            if(exists()) {
                return Methods.color(convertList(Files.MESSAGES.getFile().getStringList("Messages." + path)));
            } else {
                return Methods.color(convertList(getDefaultListMessage()));
            }
        } else {
            if(exists()) {
                return Methods.color(Files.MESSAGES.getFile().getString("Messages." + path));
            } else {
                return Methods.color(getDefaultMessage());
            }
        }
    }

    public String getMessageNoPrefix(HashMap<String, String> placeholders) {
        String message;
        if(isList()) {
            if(exists()) {
                message = Methods.color(convertList(Files.MESSAGES.getFile().getStringList("Messages." + path), placeholders));
            } else {
                message = Methods.color(convertList(getDefaultListMessage(), placeholders));
            }
        } else {
            if(exists()) {
                message = Methods.color(Files.MESSAGES.getFile().getString("Messages." + path));
            } else {
                message = Methods.color(getDefaultMessage());
            }
            for(String ph : placeholders.keySet()) {
                if(message.contains(ph)) {
                    message = message.replaceAll(ph, placeholders.get(ph)).replaceAll(ph, placeholders.get(ph).toLowerCase());
                }
            }
        }
        return message;
    }

    public static String convertList(List<String> list) {
        String message = "";
        for(String m : list) {
            message += Methods.color(m) + "\n";
        }
        return message;
    }

    public static String convertList(List<String> list, HashMap<String, String> placeholders) {
        String message = "";
        for(String m : list) {
            message += Methods.color(m) + "\n";
        }
        for(String ph : placeholders.keySet()) {
            message = Methods.color(message.replaceAll(ph, placeholders.get(ph))).replaceAll(ph, placeholders.get(ph).toLowerCase());
        }
        return message;
    }

    public static void addMissingMessages() {
        FileConfiguration messages = Files.MESSAGES.getFile();
        Boolean saveFile = false;
        for(Messages message : values()) {
            if(!messages.contains("Messages." + message.getPath())) {
                saveFile = true;
                if(message.getDefaultMessage() != null) {
                    messages.set("Messages." + message.getPath(), message.getDefaultMessage());
                } else {
                    messages.set("Messages." + message.getPath(), message.getDefaultListMessage());
                }
            }
        }
        if(saveFile) {
            Files.MESSAGES.saveFile();
        }
    }

    private Boolean exists() {
        return Files.MESSAGES.getFile().contains("Messages." + path);
    }

    private Boolean isList() {
        if(Files.MESSAGES.getFile().contains("Messages." + path)) {
            return !Files.MESSAGES.getFile().getStringList("Messages." + path).isEmpty();
        } else {
            return defaultMessage == null;
        }
    }

    private String getPath() {
        return path;
    }

    private String getDefaultMessage() {
        return defaultMessage;
    }

    private List<String> getDefaultListMessage() {
        return defaultListMessage;
    }

}
