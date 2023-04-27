package org.avience.accountmanager.bot.commands;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.avience.accountmanager.AccountManager;
import org.avience.accountmanager.ConsoleSender;
import org.avience.accountmanager.bot.DiscordBot;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

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

        else if (command.equals("secret")) { // experiment with deferreply(), THE COMMANDS WILL NOT BE AN IF ELSE STATEMENTS IN PROD

            //BotUtils botUtils = new BotUtils()
            //boolean yay = event.getMember().hasR

            //Below is how I was reading in data from the config.yml, but there is probably a better way -rob
            List<?> roleIDs = AccountManager.getInstance().getConfig().getList("SlashWhitelistAllowedRoleIDs"); //make an unspecified wildcard list
            List<Role> roles = new ArrayList<>();
            boolean hasRole = false;

            for(int i = 0; i < roleIDs.size(); i++){
                roles.add(event.getGuild().getRoleById((long) roleIDs.get(i))); //create a list of roles, attempt to cast values of roleID list to longs
            }

            for(Role r : roles){
                if(event.getMember().getRoles().contains(r)){
                    hasRole = true;
                    break;
                }
            }
            if (hasRole) {
                event.reply(event.getUser().getName() + " has the right role(s) and can use the secret command!").queue();
                ConsoleSender.sendCommand("say Secret Success!");
            } else {
                event.reply(event.getUser().getName() + " can't use the secret command!").queue();
            }

            // janky ass thingy that is a role-specific command - doesnt work
            /*boolean hasRole = false;
            List<Role> memberRoles = event.getMember().getRoles();
            //List<Long> memberRoleIDs;
            for (Role role : memberRoles) {
                 if (role.getIdLong() == 1099014264775245874L) { //role.getid equals the admin role
                     hasRole = true;
                 }
            }
            if (hasRole) {
                event.reply(event.getUser().getName() + " has the " + "[role]" + " role and can use the secret command!");
            }*/
            //if (event.getMember().)
            //event.getMember().getRoles()
        }
    }

    //Guild command

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("ping", "ping pong"));
        commandData.add(Commands.slash("secret", "only members with admin can execute this command"));
        event.getGuild().updateCommands().addCommands(commandData).queue();

        /*
         if (event.getGuild().getIdLong() == 1099013320272527420L) {
         //you can register special commands to a certain server
         // 1099013320272527420 is the Account Manager server but you could put Avience's id in there
         }
         */
        // you can put all the commands in with a list (current implementation), or you can put them in individually, separated by commas
    }
    // NOT FINAL IMPLEMENTATION!!!!! WILL MAKE A COMMAND MANAGER SYSTEM
    // THIS SYSTEM IS FOR TESTING ONLY
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("ping", "ping pong"));
        commandData.add(Commands.slash("secret", "only members with admin can execute this command"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
