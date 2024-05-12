package kr.shihyeon.diamondchecker.commands;

import kr.shihyeon.diamondchecker.config.Database;
import kr.shihyeon.diamondchecker.scoreboard.ScoreboardManager;
import kr.shihyeon.diamondchecker.util.DiamondCounter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CheckItemCommand implements CommandExecutor, TabCompleter {

    private final Database database;

    public CheckItemCommand(Database database) {
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("플레이어만 이 명령어를 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;
        if (label.equalsIgnoreCase("체크")) {
            if (database == null) {
                sender.sendMessage("데이터베이스가 초기화되지 않았습니다.");
                return true;
            }
            int totalDiamondCount = DiamondCounter.countTotalDiamonds(player.getInventory()) +
                                    DiamondCounter.countTotalDiamonds(player.getEnderChest());
            int goalDiamondCount = database.getDatabase(player);
            int remainDiamondCount = goalDiamondCount - totalDiamondCount;

            ScoreboardManager.updateScoreboard(player, totalDiamondCount, goalDiamondCount);
            player.sendMessage("§7플레이어는 §b" + totalDiamondCount + "§7개의 다이아몬드를 가지고 있습니다. (§6" + remainDiamondCount + "§7)");
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("체크");
        }
        return completions;
    }
}
