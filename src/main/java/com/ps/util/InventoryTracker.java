package com.ps.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class InventoryTracker {
    private static final Map<String, Integer> usage = new HashMap<>();
    private static final String FILE = "inventory.txt";

    // Load inventory data at the start
    public static void load() {
        File file = new File(FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    usage.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading inventory data.");
        }
    }

    // Save inventory every time an item is recorded
    private static void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE))) {
            for (Map.Entry<String, Integer> entry : usage.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving inventory data.");
        }
    }

    public static void record(String item) {
        usage.put(item, usage.getOrDefault(item, 0) + 1);
        save();
    }

    public static void printReport() {
        System.out.println("\nInventory Report:");
        for (Map.Entry<String, Integer> entry : usage.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " sold");
        }
        Logger.log("📦 Inventory report viewed");
    }

    public static void reset() {
        usage.clear();
        save();
    }
}
