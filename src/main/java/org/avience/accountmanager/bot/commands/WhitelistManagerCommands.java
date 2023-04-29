package org.avience.accountmanager.bot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.avience.accountmanager.ConsoleSender;

public class WhitelistManagerCommands extends CommandManager {
// No way to see the command feedback from the server yet so can't confirm what happened, for now just pretend everything went well
    public WhitelistManagerCommands(String subCommand, SlashCommandInteractionEvent event){
        if(subCommand.equals("on")){
            ConsoleSender.sendCommand("whitelist on");
            event.reply("The whitelist was enabled!").queue();
        }
        else if(subCommand.equals("off")){
            ConsoleSender.sendCommand("whitelist off");
            event.reply("The whitelist was disabled!").queue();
        }
    }
    public WhitelistManagerCommands(String subCommand, String username, SlashCommandInteractionEvent event){
        if(subCommand.equals("add")){
            ConsoleSender.sendCommand("whitelist add " + username);
            event.reply("Added " + username + " to the whitelist!").queue();
        }
        else if(subCommand.equals("remove")){
            ConsoleSender.sendCommand("whitelist remove " + username);
            event.reply("Removed " + username + " from the whitelist!").queue();
        }
    }
}
