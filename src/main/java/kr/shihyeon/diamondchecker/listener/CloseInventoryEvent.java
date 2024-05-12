package kr.shihyeon.diamondchecker.listener;

import kr.shihyeon.diamondchecker.config.Database;
import kr.shihyeon.diamondchecker.scheduler.EventDelayScheduler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CloseInventoryEvent implements Listener {

    public CloseInventoryEvent(Database database) {
        EventDelayScheduler.setDatabase(database);
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            EventDelayScheduler.scheduleItem(player);
        }
    }
}
