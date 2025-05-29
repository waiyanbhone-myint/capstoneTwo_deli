package com.ps.util;

import com.ps.model.Order;
import com.ps.model.Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {

    public static void saveReceipt(Order order) {
        try {
            File directory = new File("receipts");
            if (!directory.exists()) {
                directory.mkdir();
            }

            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String readableDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String readableTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String filename = "receipts/" + timestamp + ".txt";

            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            writer.write("========================================\n");
            writer.write("            DELI-cious POS\n");
            writer.write("         123 Sandwich Blvd, NYC\n");
            writer.write("         Tel: (555) 123-4567\n\n");

            writer.write("        🧾 ORDER RECEIPT 🧾\n\n");
            writer.write("Date: " + readableDate + "\n");
            writer.write("Time: " + readableTime + "\n");
            writer.write("----------------------------------------\n");

            for (Product item : order.getItems()) {
                writer.write(item.getDetails() + "\n");
                writer.write("----------------------------------------\n");
            }

            writer.write(String.format("\nTOTAL:%30s\n", "$" + String.format("%.2f", order.getTotalPrice())));
            writer.write("Thank you for ordering with us!\n");
            writer.write("========================================\n");

            writer.close();

            System.out.println("📄 Receipt saved to " + filename);

        } catch (IOException e) {
            System.out.println("❌ Failed to write receipt: " + e.getMessage());
        }
    }
}
