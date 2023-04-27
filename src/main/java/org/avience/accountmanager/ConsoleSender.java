package org.avience.accountmanager;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ConsoleSender {
    public static void sendCommand(String command){
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
            }
        }.runTask(AccountManager.getInstance());
    }
}
