package com.ps.util;

import java.util.HashMap;
import java.util.Map;

public class InventoryTracker {
    private static final Map<String, Integer> usage = new HashMap<>();

    public static void record(String item) {
        usage.put(item, usage.getOrDefault(item, 0) + 1);
    }

    public static void printReport() {
        System.out.println("\nInventory Report:");
        for (Map.Entry<String, Integer> entry : usage.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " sold");
        }
    }

    public static void reset() {
        usage.clear();
    }
}
