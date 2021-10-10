package com.timidoskekw.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand extends Command {

    public JoinCommand() {
        this.name = "join";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        MessageReceivedEvent event = commandEvent.getEvent();
        MessageChannel channel = commandEvent.getChannel();
        if (!event.getGuild().getSelfMember().hasPermission((GuildChannel) channel, Permission.VOICE_CONNECT)) {
            channel.sendMessage("Sem permissão para conectar ao canal de voz").queue();
            return;
        }

        VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
        if (connectedChannel == null) {
            channel.sendMessage("É necessário estar conectado a um canal de voz").queue();
            return;
        }

        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.openAudioConnection(connectedChannel);
        channel.sendMessage("Conectado ao canal de voz").queue();
    }



}
