package service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static final String AUDIT_FILE = "audit.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logAction(String action) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AUDIT_FILE, true))) {
            String timestamp = LocalDateTime.now().format(formatter);
            writer.println(action + "," + timestamp);
        } catch (IOException e) {
            System.out.println("Error writing to audit file: " + e.getMessage());
        }
    }
}
