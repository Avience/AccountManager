package org.avience.accountmanager.bot.commands;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event){
        String command = event.getName();
        String subCommand = "none";
        if(event.getSubcommandName() != null){subCommand = event.getSubcommandName();}

        switch (command) {
            case "whitelist" :
                if(subCommand.equals("add") || subCommand.equals("remove")){
                    new WhitelistManagerCommands(subCommand, Objects.requireNonNull(event.getOption("username")).getAsString(), event);
                }
                else if(subCommand.equals("on") || subCommand.equals("off")){
                    new WhitelistManagerCommands(subCommand, event);
                }
                break;
        }
    }

    public List<CommandData> getCommandData(){
        List<CommandData> commandData = new ArrayList<>();
        //Start Whitelist Command
        commandData.add(Commands.slash("whitelist", "whitelist command")
                .addSubcommands(new SubcommandData("add", "add a user to the whitelist")
                        .addOption(OptionType.STRING, "username", "the username to whitelist", true))
                .addSubcommands(new SubcommandData("remove", "remove a user from the whitelist")
                        .addOption(OptionType.STRING, "username", "the username to unwhitelist", true))
                .addSubcommands(new SubcommandData("on", "turn the whitelist on"))
                .addSubcommands(new SubcommandData("off", "turn the whitelist off")));
        //End Whitelist Command
        return commandData;
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = getCommandData();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        List<CommandData> commandData = getCommandData();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
