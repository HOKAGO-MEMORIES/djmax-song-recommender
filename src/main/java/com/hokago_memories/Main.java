package com.hokago_memories;


import com.hokago_memories.config.AppConfig;
import com.hokago_memories.controller.WebController;
import com.hokago_memories.service.SongDataInitializerService;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        SongDataInitializerService initializer = appConfig.songDataInitializerService();
        initializer.initializeDatabaseIfNeeded();

        /* CLI에서 사용
         * CliController controller = appConfig.cliController();
         * controller.run();
         */

        WebController controller = appConfig.webController();
        int port = getPort();
        controller.start(port);
    }

    private static int getPort() {
        String portEnv = System.getenv("PORT");
        if (portEnv != null && !portEnv.isEmpty()) {
            return Integer.parseInt(portEnv);
        }
        return 3939;
    }
}
