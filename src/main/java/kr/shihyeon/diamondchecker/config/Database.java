package kr.shihyeon.diamondchecker.config;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Database extends Configuration {

    public Database(Plugin plugin) {
        super(plugin, "database.yml");
        load();
    }

    public int getDatabase(Player player) {
        return config.getInt(player.getUniqueId().toString(), 0);
    }

    public void setDatabase(Player player, int amount) {
        config.set(player.getUniqueId().toString(), amount);
        save();
    }

    public void addDatabase(Player player, int amount) {
        int current = getDatabase(player);
        setDatabase(player, current + amount);
    }

    public void removeDatabase(Player player, int amount) {
        int current = getDatabase(player);
        setDatabase(player, Math.max(current - amount, 0));
    }

    @Override
    public void load() {
        if (!file.exists()) {
            save();
        }
    }
}
