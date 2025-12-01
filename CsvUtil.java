import java.io.FileWriter;
import java.util.List;

public class CsvUtil {

    public static void exportLibraryCSV(Library lib, String filepath) {
        try (FileWriter fw = new FileWriter(filepath)) {

            // Books
            fw.write("Books\\n");
            fw.write("ID,Title,Author,TotalCopies,AvailableCopies\\n");
            for (Book b : lib.listAllBooks()) {
                fw.write(String.format("%d,\"%s\",\"%s\",%d,%d\\n",
                        b.getId(),
                        b.getTitle().replace("\"", "\"\""),
                        b.getAuthor().replace("\"", "\"\""),
                        b.getTotalCopies(),
                        b.getAvailableCopies()));
            }
            fw.write("\\n");

            // Members
            fw.write("Members\\n");
            fw.write("ID,Name,Contact\\n");
            for (Member m : lib.listAllMembers()) {
                fw.write(String.format("%d,\"%s\",\"%s\"\\n",
                        m.getId(),
                        m.getName().replace("\"", "\"\""),
                        m.getContact().replace("\"", "\"\"")));
            }
            fw.write("\\n");

            // Loans
            fw.write("Loans\\n");
            fw.write("BookID,MemberID,IssueDate,DueDate\\n");
            for (Loan ln : lib.listAllLoans()) {
                fw.write(String.format("%d,%d,%s,%s\\n",
                        ln.getBookId(), ln.getMemberId(),
                        ln.getIssueDate().toString(), ln.getDueDate().toString()));
            }

            System.out.println("Exported library to CSV file: " + filepath);
        } catch (Exception e) {
            System.out.println("Error exporting CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
