package org.avience.accountmanager.bot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import org.avience.accountmanager.AccountManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class DiscordBot {
    private JDA jda;

    public DiscordBot(AccountManager plugin){
        FileConfiguration config = plugin.getConfig();
        String botToken = config.getString("DiscordBotToken");
        plugin.saveConfig();
        try {
            jda = JDABuilder.createDefault(botToken).build();
        }
        catch (InvalidTokenException e){
            Bukkit.getLogger().warning("[AccountManager] Failed to start discord bot, are you sure the bot token in config.yml is correct?");
        }
    }

    public JDA getJDA(){
        return jda;
    }

    /*public static void main(String[] args) { //only for ease of development for bot
        //FileConfiguration config = AccountManagerConfig.getMainConfig();
        Dotenv dotenv = Dotenv.load();
        String botToken = dotenv.get("DISCORD_TOKEN");
        JDA jda = JDABuilder.createDefault(botToken).build();
    }*/
}
