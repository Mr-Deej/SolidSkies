package co.matrixdevelopment.solidskies.events;

import java.util.SplittableRandom;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class VillagerDeathListener implements Listener {

    private static final SplittableRandom RAND = new SplittableRandom();

    @EventHandler
    public void onVDeath(EntityDeathEvent e)
    {
        if (e.getEntityType() == EntityType.VILLAGER) {
            e.getDrops().add(new ItemStack(Material.EMERALD, RAND.nextInt(1, 4)));
        }
    }
}
