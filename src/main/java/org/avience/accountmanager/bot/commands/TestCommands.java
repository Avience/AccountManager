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

        else if (command.equals("secret")) { // THE COMMANDS WILL NOT BE AN IF ELSE STATEMENTS IN PROD

            //event.deferReply().queue();
            //if you want to have the deferred reply be ephemeral, you have to do .setEphemeral(true) on .deferReply
            //if you do it on the event.getHook().sendMessage(), it WILL NOT WORK!!

            //Below is how I was reading in data from the config.yml, but there is probably a better way -rob

            // uncomment the segment below if using the server to run
            /*List<?> roleIDs = AccountManager.getInstance().getConfig().getList("SlashWhitelistAllowedRoleIDs"); //make an unspecified wildcard list
            List<Role> roles = new ArrayList<>();*/

            // uncomment the segment below if using main method to run (testing)

            List<Long> roleIDs = new ArrayList<>();
            List<Role> roles = new ArrayList<>();
            roleIDs.add(1099014264775245874L);
            roleIDs.add(1082766305070960730L);
            roleIDs.add(517167620089315338L);
            roleIDs.add(517871520597409814L);
            roleIDs.add(999697940123746365L);
            roleIDs.add(517330988934889473L);
            roleIDs.add(517165622711287859L);
            roleIDs.add(1101141794286215228L);

            boolean hasRole = false;

            // probably possible to use for-each
            for (int i = 0; i < roleIDs.size(); i++) { // do NOT remove the cast to long
                roles.add(event.getGuild().getRoleById((long) roleIDs.get(i))); //create a list of roles, attempt to cast values of roleID list to longs
            }

            for (Role r : roles) {
                if (event.getMember().getRoles().contains(r)) {
                    hasRole = true;
                    break;
                }
            }
            if (hasRole) {
                event.reply(event.getUser().getName() + " has the right role(s) and can use the secret command!").setEphemeral(true).queue();
                ConsoleSender.sendCommand("say Secret Success!");
            } else {
                event.reply(event.getUser().getName() + " can't use the secret command!").setEphemeral(true).queue();
                // deferreply example
                // event.getHook().sendMessage(event.getUser().getName() + " can't use the secret command!").queue();
            }
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