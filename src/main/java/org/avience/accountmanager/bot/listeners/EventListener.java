package org.avience.accountmanager.bot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

//may not use package/class in final bot
public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        //String rob = "Rob#3704";
        String content = event.getMessage().getContentRaw();
        if (content.equals("ping")) {
            //MessageChannel channel = event.getChannel();
            event.getChannel().sendMessage("pong").queue();
        }
    }
}
