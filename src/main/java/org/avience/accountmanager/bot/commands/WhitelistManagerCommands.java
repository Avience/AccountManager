package org.avience.accountmanager.bot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import org.avience.accountmanager.ConsoleSender;

public class WhitelistManagerCommands extends CommandManager {
    public String output = "none"; //dummy set until I can figure out how to get this to set properly at the right time for now this still does not work and will output the default message for all commands
    public WhitelistManagerCommands(String subCommand, SlashCommandInteractionEvent event){
        if(subCommand.equals("on")){
            ConsoleSender.sendCommand("whitelist on");
            if(output.equals("Whitelist is already turned on")){
                event.reply("The whitelist is already enabled!").queue();
            }
            else{
                event.reply("The whitelist is enabled!").queue();
            }
        }
        else if(subCommand.equals("off")){
            ConsoleSender.sendCommand("whitelist off");
            if(output.equals("Whitelist is already turned off")){
                event.reply("The whitelist is already disabled!").queue();
            }
            else{
                event.reply("The whitelist is disabled!").queue();
            }
        }
    }
    public WhitelistManagerCommands(String subCommand, String username, SlashCommandInteractionEvent event){
        if(subCommand.equals("add")){
            ConsoleSender.sendCommand("whitelist add " + username);
            if(output.equals("Player is already whitelisted")){
                event.reply(username + " is already whitelisted!").queue();
            }
            else{
                event.reply("Added " + username + " to the whitelist!").queue();
            }
        }
        else if(subCommand.equals("remove")){
            ConsoleSender.sendCommand("whitelist remove " + username);
            if(output.equals("Player is not whitelisted")){
                event.reply(username + " is already not whitelisted!").queue();
            }
            else{
                event.reply("Removed " + username + " from the whitelist!").queue();
            }
        }
    }
}

