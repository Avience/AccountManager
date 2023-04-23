package org.avience.accountmanager;

import org.avience.accountmanager.bot.DiscordBot;
import org.bukkit.plugin.java.JavaPlugin;

public final class AccountManager extends JavaPlugin {

    public static void main(String[] args) {
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        DiscordBot bot = new DiscordBot();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
