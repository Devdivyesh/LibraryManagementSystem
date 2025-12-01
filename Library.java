import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Loan> loans = new ArrayList<>();
    private int nextBookId = 1;
    private int nextMemberId = 1;

    public void addBook(String title, String author, int copies) {
        Book b = new Book(nextBookId++, title, author, copies);
        books.add(b);
        System.out.println("Added: " + b);
    }

    public void registerMember(String name, String contact) {
        Member m = new Member(nextMemberId++, name, contact);
        members.add(m);
        System.out.println("Registered: " + m);
    }

    public Book findBookById(int id) {
        for (Book b : books) if (b.getId() == id) return b;
        return null;
    }

    public Member findMemberById(int id) {
        for (Member m : members) if (m.getId() == id) return m;
        return null;
    }

    public List<Book> searchBooksByTitle(String q) {
        q = q.toLowerCase();
        ArrayList<Book> res = new ArrayList<>();
        for (Book b : books) if (b.getTitle().toLowerCase().contains(q)) res.add(b);
        return res;
    }

    public List<Book> listAllBooks() {
        return new ArrayList<>(books);
    }

    public List<Member> listAllMembers() {
        return new ArrayList<>(members);
    }

    public List<Loan> listAllLoans() {
        return new ArrayList<>(loans);
    }

    public boolean issueBook(int bookId, int memberId, int days) {
        Book b = findBookById(bookId);
        Member m = findMemberById(memberId);
        if (b == null) { System.out.println("Book not found"); return false; }
        if (m == null) { System.out.println("Member not found"); return false; }
        if (!b.issueOne()) { System.out.println("No copies available"); return false; }
        LocalDate now = LocalDate.now();
        Loan loan = new Loan(bookId, memberId, now, now.plusDays(days));
        loans.add(loan);
        System.out.println("Issued: " + loan);
        return true;
    }

    public boolean returnBook(int bookId, int memberId) {
        Iterator<Loan> it = loans.iterator();
        while (it.hasNext()) {
            Loan ln = it.next();
            if (ln.getBookId() == bookId && ln.getMemberId() == memberId) {
                it.remove();
                Book b = findBookById(bookId);
                if (b != null) b.returnOne();
                System.out.println("Returned: " + ln);
                return true;
            }
        }
        System.out.println("Loan record not found for given book/member");
        return false;
    }

    public void saveToFile(String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            System.out.println("Library saved to " + filename);
        } catch (Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public static Library loadFromFile(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object o = ois.readObject();
            if (o instanceof Library) {
                System.out.println("Library loaded from " + filename);
                return (Library) o;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No save file found, starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading: " + e.getMessage());
        }
        return new Library();
    }
}
