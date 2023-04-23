package org.avience.accountmanager.bot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String botToken = dotenv.get("DISCORD_TOKEN");
        JDA jda = JDABuilder.createDefault(botToken).build();
    }
}
