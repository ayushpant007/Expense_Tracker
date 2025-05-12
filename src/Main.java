import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        while (true) {
            System.out.println("===== Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Total");
            System.out.println("4. Exit");
            System.out.println("5. Export to CSV");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ₹");
                    double amount = scanner.nextDouble();
                    tracker.addExpense(category, amount);
                    break;
                case 2:
                    tracker.viewExpenses();
                    break;
                case 3:
                    System.out.println("💰 Total Spent: ₹" + tracker.getTotal() + "\n");
                    break;
                case 4:
                    System.out.println("👋 Exiting. Goodbye!");
                    return;
                case 5:
                    tracker.exportToCSV();
                    break;
                default:
                    System.out.println("❌ Invalid option. Try again.\n");
            }
        }
    }
}
