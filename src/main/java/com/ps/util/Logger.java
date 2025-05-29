package com.ps.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    private static final String LOG_FOLDER = "logs";
    private static final String LOG_FILE = "logs/log.txt";

    public static void log(String message) {
        try {
            File dir = new File(LOG_FOLDER);
            if (!dir.exists()) {
                dir.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
            String timestamp = LocalDateTime.now().toString();
            writer.write("[" + timestamp + "] " + message + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("❌ Failed to write log: " + e.getMessage());
        }
    }
}
