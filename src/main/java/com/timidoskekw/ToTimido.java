package com.timidoskekw;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class ToTimido {

    public static void main(String[] args) throws LoginException {
        JDABuilder.createLight(Config.get("token"))
                .addEventListeners(new Listener())
                .setActivity(Activity.playing("Hello World!"))
                .build();

    }

}
