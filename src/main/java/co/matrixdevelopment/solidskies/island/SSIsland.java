package co.matrixdevelopment.solidskies.island;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SSIsland {

    private int level;
    private int x;
    private int y;
    private Player owner;
    private ArrayList<Player> party;

    private int blockCount;

    public SSIsland(int x, int y, Player owner) {
        this.x = x;
        this.y = y;
        this.owner = owner;

        Location loc = new Location(Bukkit.getWorld("SolidSkies"), x, y, 5);
        loc.getBlock().setType(Material.DIRT);
    }

    public void updateBlockCount() {
        // Location loc = new Location(getS, x, y, z)
        //TODO: Actually make this work
        blockCount = 5;
    }

    /**
     * Adds a player to the party
     * @param p the player to add
     * @return whether the addition was successful
     */
    public boolean addPlayerToParty(Player p) {
        if (party.size() >= 4) {
            return false;
        }
        party.add(p);
        return true;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @return the party
     */
    public ArrayList<Player> getParty() {
        return party;
    }

    /**
    * @return the x
    */
    public int getX() {
        return x;
    }

    /**
    * @return the y
    */
    public int getY() {
        return y;
    }

    /**
     * @return the blockCount
     */
    public int getBlockCount() {
        return blockCount;
    }
}