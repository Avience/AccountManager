package org.avience.accountmanager.bot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot {
    public DiscordBot(){
        //String botToken will be read from a yml file later on and loaded here from onEnable() in the main project class
        //JDA jda = JDABuilder.createDefault(botToken).build();
    }

    public static void main(String[] args) { //only for development of bot
        Dotenv dotenv = Dotenv.load();
        String botToken = dotenv.get("DISCORD_TOKEN");
        JDA jda = JDABuilder.createDefault(botToken).build();
    }
}
