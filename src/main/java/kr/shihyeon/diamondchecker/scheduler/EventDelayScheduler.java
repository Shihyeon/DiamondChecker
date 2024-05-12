package kr.shihyeon.diamondchecker.scheduler;

import kr.shihyeon.diamondchecker.config.Database;
import kr.shihyeon.diamondchecker.DiamondCheckerPlugin;
import kr.shihyeon.diamondchecker.scoreboard.ScoreboardManager;
import kr.shihyeon.diamondchecker.util.DiamondCounter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EventDelayScheduler {

    private static Database database;

    public static void setDatabase(Database db) {
        database = db;
    }

    public static void scheduleItem(Player player) {
        Bukkit.getScheduler().runTaskLater(DiamondCheckerPlugin.getInstance(), () -> {
            if (database == null) {
                System.out.println("Database is not initialized!");
                return;
            }

            int totalDiamondCount = DiamondCounter.countTotalDiamonds(player.getInventory()) +
                                    DiamondCounter.countTotalDiamonds(player.getEnderChest());
            int goalDiamondCount = database.getDatabase(player);

            ScoreboardManager.updateScoreboard(player, totalDiamondCount, goalDiamondCount);
        }, 1L); // 1 틱 후에 실행
    }
}
