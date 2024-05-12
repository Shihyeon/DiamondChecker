package kr.shihyeon.diamondchecker.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DiamondCounter {

    public static int countTotalDiamonds(Inventory inventory) {
        int diamondCount = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                if (item.getType() == Material.DIAMOND) {
                    diamondCount += item.getAmount();
                } else if (item.getType() == Material.DIAMOND_BLOCK) {
                    diamondCount += item.getAmount() * 9;
                }
            }
        }
        return diamondCount;
    }
}
