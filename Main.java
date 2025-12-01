import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String SAVE_FILE = "library.ser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = Library.loadFromFile(SAVE_FILE);

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choice> ");
            String ch = sc.nextLine().trim();
            switch (ch) {
                case "1":
                    System.out.print("Book title: ");
                    String title = sc.nextLine().trim();
                    System.out.print("Author: ");
                    String author = sc.nextLine().trim();
                    System.out.print("Number of copies: ");
                    int copies = Integer.parseInt(sc.nextLine().trim());
                    lib.addBook(title, author, copies);
                    break;
                case "2":
                    System.out.print("Search query (title): ");
                    String q = sc.nextLine().trim();
                    List<Book> res = lib.searchBooksByTitle(q);
                    if (res.isEmpty()) System.out.println("No books found.");
                    else for (Book b : res) System.out.println(b);
                    break;
                case "3":
                    System.out.print("Member name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Contact info: ");
                    String contact = sc.nextLine().trim();
                    lib.registerMember(name, contact);
                    break;
                case "4":
                    System.out.println("All books:");
                    for (Book b : lib.listAllBooks()) System.out.println(b);
                    break;
                case "5":
                    System.out.println("All members:");
                    for (Member m : lib.listAllMembers()) System.out.println(m);
                    break;
                case "6":
                    System.out.print("Book ID to issue: ");
                    int bid = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Member ID: ");
                    int mid = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Days for loan: ");
                    int days = Integer.parseInt(sc.nextLine().trim());
                    lib.issueBook(bid, mid, days);
                    break;
                case "7":
                    System.out.print("Book ID to return: ");
                    int rb = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Member ID: ");
                    int rm = Integer.parseInt(sc.nextLine().trim());
                    lib.returnBook(rb, rm);
                    break;
                case "8":
                    System.out.println("Active loans:");
                    for (Loan ln : lib.listAllLoans()) System.out.println(ln);
                    break;
                case "9":
                    lib.saveToFile(SAVE_FILE);
                    running = false;
                    break;
                case "10":
                    System.out.print("Enter output CSV filename (e.g. library.csv): ");
                    String csv = sc.nextLine().trim();
                    if (!csv.toLowerCase().endsWith(".csv")) csv += ".csv";
                    CsvUtil.exportLibraryCSV(lib, csv);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }
        sc.close();
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("=== Library Management System ===");
        System.out.println("1. Add a book");
        System.out.println("2. Search books by title");
        System.out.println("3. Register member");
        System.out.println("4. List all books");
        System.out.println("5. List all members");
        System.out.println("6. Issue a book");
        System.out.println("7. Return a book");
        System.out.println("8. List active loans");
        System.out.println("9. Save & Exit");
        System.out.println("10. Export data to CSV"); 
    }
}
