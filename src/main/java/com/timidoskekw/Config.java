package com.timidoskekw;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.load();

    public static final String get(String key) {
        return dotenv.get(key);
    }

}
