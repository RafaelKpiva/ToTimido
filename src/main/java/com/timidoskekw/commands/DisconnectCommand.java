package com.timidoskekw.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class DisconnectCommand extends Command {

    public DisconnectCommand() {
        this.name = "disconnect";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        var event = commandEvent.getEvent();
        var self = commandEvent.getSelfMember();
        var member = commandEvent.getMember();

        if (isSameVoiceChannel(self, member)) {
            AudioManager audioManager = event.getGuild().getAudioManager();
            if (audioManager.isConnected())
                audioManager.closeAudioConnection();
            else
                self.getGuild().moveVoiceMember(self, null).queue();
        }

    }

    private boolean isSameVoiceChannel(Member self, Member member) {
        var selfMemberVoiceChannel = self.getVoiceState().getChannel();
        VoiceChannel memberVoiceChannel = member.getVoiceState().getChannel();

        return memberVoiceChannel.getId().equals(selfMemberVoiceChannel.getId());
    }

}
