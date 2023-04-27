package org.avience.accountmanager.bot.commands;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TestCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("ping")) { // command /ping

            if (event.getMember().getNickname() == null) { // if the user doesnt have a nickname
                String username = event.getUser().getName(); //.getName() gets name, .getID() gets user#0000
                event.reply(username + " pong").queue();
            } else { // if the user has a nickname
                String nickname = event.getMember().getNickname();
                event.reply(nickname + " pong").queue();
            }
            // if command with .reply() takes longer than 3 seconds to execute, discord will throw an error or smth
            // use .deferReply to make discord wait for commands that take a long time to execute
        }

        else if (command.equals("whitelist")) { // experiment with deferreply(), THE COMMANDS WILL NOT BE AN IF ELSE STATEMENTS IN PROD

        }
    }

    //Guild command

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("ping", "ping pong"));


        /*
         if (event.getGuild().getIdLong() == 1099013320272527420L) {
         //you can register special commands to a certain server
         // 1099013320272527420 is the Account Manager server but you could put Avience's id in there
         }
         */
        // you can put all the commands in with a list (current implementation), or you can put them in individually, separated by commas
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
    // NOT FINAL IMPLEMENTATION!!!!! WILL MAKE A COMMAND MANAGER SYSTEM
    // THIS SYSTEM IS FOR TESTING ONLY
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("ping", "ping pong"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
