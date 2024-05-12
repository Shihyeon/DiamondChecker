package kr.shihyeon.diamondchecker.listener;

import kr.shihyeon.diamondchecker.config.Database;
import kr.shihyeon.diamondchecker.scheduler.EventDelayScheduler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemEvent implements Listener {

    public DropItemEvent(Database database) {
        EventDelayScheduler.setDatabase(database);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        EventDelayScheduler.scheduleItem(player);
    }
}