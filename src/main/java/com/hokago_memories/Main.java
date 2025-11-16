package com.hokago_memories;


import com.hokago_memories.config.AppConfig;
import com.hokago_memories.controller.CliController;
import com.hokago_memories.service.SongDataInitializerService;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        SongDataInitializerService initializer = appConfig.songDataInitializerService();
        initializer.initializeDatabaseIfNeeded();

        CliController controller = appConfig.cliController();
        controller.run();
    }
}
