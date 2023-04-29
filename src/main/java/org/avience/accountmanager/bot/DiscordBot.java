package org.avience.accountmanager.bot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.avience.accountmanager.AccountManager;
import org.avience.accountmanager.bot.commands.CommandManager;
import org.avience.accountmanager.bot.commands.TestCommands;
import org.avience.accountmanager.bot.listeners.EventListener;
import org.bukkit.Bukkit;

public class DiscordBot {
    private JDA jda;

    public DiscordBot(AccountManager plugin){
        String botToken = plugin.getConfig().getString("DiscordBotToken");
        try {
            //enabling the intent MESSAGE_CONTENT allows it to get the content of messages sent or something idk i dont really understand
            //see https://jda.wiki/using-jda/gateway-intents-and-member-cache-policy/
            jda = JDABuilder.createDefault(botToken)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .setActivity(Activity.playing("Avience"))
                    //.setMemberCachePolicy(MemberCachePolicy.ALL) //caches members with lazy loading, not currently needed
                    //.setChunkingFilter(ChunkingFilter.ALL) // caches EVERYTHING at once, not lazy loading, memory intensive but necessary sometimes
                    //.enableCache(CacheFlag.) //put in what you want to cache
                    .build();
        }
        catch (InvalidTokenException e){
            Bukkit.getLogger().warning("[AccountManager] Failed to start discord bot, are you sure the bot token in config.yml is correct?");
        }

        //register listener (may or may not use in final bot, depends on if it has a function thats not a slash command
        if(jda != null){
            jda.addEventListener(new EventListener(), new CommandManager()); //CHANGED TO TEST COMMANDMANAGER
        }
    }

    public JDA getJDA(){
        return jda;
    }

    public static void main(String[] args) { //only for ease of development for bot
        //FileConfiguration config = AccountManagerConfig.getMainConfig();
        Dotenv dotenv = Dotenv.load();
        String botToken = dotenv.get("DISCORD_TOKEN");
        //enabling the intent MESSAGE_CONTENT allows it to get the content of messages sent or something idk i dont really understand
        //see https://jda.wiki/using-jda/gateway-intents-and-member-cache-policy/
        JDA jda = JDABuilder.createDefault(botToken)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing("Avience"))
                //.setMemberCachePolicy(MemberCachePolicy.ALL) //caches members with lazy loading, not currently needed
                //.enableCache(CacheFlag.) put in what you want to cache
                .build();

        //idk if redoing the jdabuilder and addeventlistener in the main method (its in the constructor too) messes anything up but it hasnt so far to my knowledge
        //it shouldnt i think
        jda.addEventListener(new EventListener(), new CommandManager()); //CHANGED TO TEST COMMANDMANAGER
    }
}
