package kr.shihyeon.diamondchecker.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {

    public static void updateScoreboard(Player player, int totalDiamondCount, int goalDiamondCount) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("diamonds", "dummy", "§l다이아 켠왕");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore("").setScore(3);
        objective.getScore(player.getName()).setScore(2);
        int remainDiamondCount = goalDiamondCount - totalDiamondCount;
        objective.getScore("현재: §b" + totalDiamondCount + "§f개").setScore(1);
        objective.getScore("목표: §4" + goalDiamondCount + "§f개 " + "(§6" + remainDiamondCount + "§f)").setScore(0);
        player.setScoreboard(scoreboard);
    }
}
