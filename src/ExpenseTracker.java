import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;

public class ExpenseTracker {
    private List<Expense> expenses = new ArrayList<>();
    private final String FILE_NAME = "expenses.txt";

    public ExpenseTracker() {
        loadFromFile();
    }

    public void addExpense(String category, double amount) {
        Expense expense = new Expense(category, amount, LocalDate.now());
        expenses.add(expense);
        saveToFile();
        System.out.println("✅ Expense added and saved.\n");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.\n");
        } else {
            System.out.println("📋 All Expenses:");
            for (Expense e : expenses) {
                System.out.println(e);
            }
            System.out.println();
        }
    }

    public double getTotal() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                writer.println(e.getDate() + "," + e.getCategory() + "," + e.getAmount());
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    expenses.add(new Expense(category, amount, date));
                }
            }
            System.out.println("📂 Loaded " + expenses.size() + " expense(s) from file.\n");
        } catch (IOException e) {
            System.out.println("📁 No saved expenses found. Starting fresh.\n");
        }
    }

    // Export expenses to a CSV file
    public void exportToCSV() {
        String csvFile = "expenses.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println("Date,Category,Amount");
            for (Expense e : expenses) {
                writer.println(e.getDate() + "," + e.getCategory() + "," + e.getAmount());
            }
            System.out.println("📁 Exported to " + csvFile + " successfully.\n");
        } catch (IOException e) {
            System.out.println("❌ Error exporting to CSV: " + e.getMessage());
        }
    }
}
