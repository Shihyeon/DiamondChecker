package kr.shihyeon.diamondchecker.commands;

import kr.shihyeon.diamondchecker.config.Database;
import kr.shihyeon.diamondchecker.scheduler.EventDelayScheduler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class DiamondGoalCommand implements CommandExecutor, TabCompleter {

    private final Database database;

    public DiamondGoalCommand(Database database) {
        this.database = database;
        EventDelayScheduler.setDatabase(database);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 2) {
            sendUsage(player, label);
            return true;
        }

        String action = args[0].toLowerCase();
        int amount;

        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage("개수는 숫자로 입력해주세요.");
            return true;
        }

        String resultMessage = "";
        int currentAmount = database.getDatabase(player);

        switch (action) {
            case "추가":
                database.addDatabase(player, amount);
                currentAmount += amount;
                resultMessage = "§7목표 다이아몬드 개수가 §f" + amount + "§7만큼 증가했습니다. (§4" + currentAmount + "§7개)";
                break;
            case "제거":
                database.removeDatabase(player, amount);
                currentAmount -= amount;
                resultMessage = "§7목표 다이아몬드 개수가 §f" + amount + "§7만큼 감소했습니다. (§4" + currentAmount + "§7개)";
                break;
            case "설정":
                database.setDatabase(player, amount);
                currentAmount = amount;
                resultMessage = "§7목표 다이아몬드 개수가 §f" + amount + "§7으로 설정되었습니다. (§4" + currentAmount + "§7개)";
                break;
            default:
                sendUsage(player, label);
        }
        player.sendMessage(resultMessage);
        EventDelayScheduler.scheduleItem(player);
        return true;
    }

    private void sendUsage(Player player, String label) {
        player.sendMessage("올바른 사용법: /" + label + " <추가/제거/설정> <개수>");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("추가", "제거", "설정");
        } else if (args.length == 2 && Arrays.asList("추가", "제거", "설정").contains(args[0].toLowerCase())) {
            return Arrays.asList("<개수>");
        }
        return null;
    }
}
