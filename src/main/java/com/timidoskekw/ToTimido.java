package com.timidoskekw;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.timidoskekw.commands.DisconnectCommand;
import com.timidoskekw.commands.JoinCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class ToTimido {

    public static void main(String[] args) throws LoginException {
        CommandClient commandClient = buildCommandClient();
        JDABuilder.createDefault(
                        Config.get("token"),
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(commandClient)
                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.playing("Hello World!"))
                .build();

    }

    private static CommandClient buildCommandClient() {
        CommandClientBuilder commandClientBuilder = new CommandClientBuilder();

        commandClientBuilder.setPrefix(Config.get("prefix"));
        commandClientBuilder.addCommand(new JoinCommand());
        commandClientBuilder.addCommand(new DisconnectCommand());
        commandClientBuilder.setOwnerId(Config.get("owner_id"));

        return commandClientBuilder.build();
    }

}
