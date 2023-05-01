package org.avience.accountmanager;

import org.avience.accountmanager.bot.DiscordBot;

import org.bukkit.plugin.java.JavaPlugin;

public final class AccountManager extends JavaPlugin {
    private static AccountManager instance;
    private static DiscordBot bot;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        bot = new DiscordBot(instance);
    }

    @Override
    public void onDisable() {
        bot.getJDA().shutdown();
    }

    public static AccountManager getInstance() {
        return instance;
    }
}
