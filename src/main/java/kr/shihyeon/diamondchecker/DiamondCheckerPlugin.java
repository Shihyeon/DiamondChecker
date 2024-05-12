package kr.shihyeon.diamondchecker;

import kr.shihyeon.diamondchecker.commands.CheckItemCommand;
import kr.shihyeon.diamondchecker.commands.DiamondGoalCommand;
import kr.shihyeon.diamondchecker.config.Database;
import kr.shihyeon.diamondchecker.listener.CloseInventoryEvent;
import kr.shihyeon.diamondchecker.listener.DropItemEvent;
import kr.shihyeon.diamondchecker.listener.PickupEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DiamondCheckerPlugin extends JavaPlugin {

    private static DiamondCheckerPlugin instance;
    private Database database;

    @Override
    public void onEnable() {
        instance = this;

        database = new Database(this);

        Bukkit.getPluginManager().registerEvents(new DropItemEvent(database), this);
        Bukkit.getPluginManager().registerEvents(new PickupEvent(database), this);
        Bukkit.getPluginManager().registerEvents(new CloseInventoryEvent(database), this);

        getCommand("체크").setExecutor(new CheckItemCommand(database));
        getCommand("다이아").setExecutor(new DiamondGoalCommand(database));

        getLogger().info("DiamondCheckerPlugin is enabled.");
    }

    @Override
    public void onDisable() {
        database.save();

        getLogger().info("DiamondCheckerPlugin is disabled.");
    }

    public static DiamondCheckerPlugin getInstance() {
        return instance;
    }
}
