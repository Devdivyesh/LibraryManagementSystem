import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public Book(int id, String title, String author, int copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.totalCopies = copies;
        this.availableCopies = copies;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }

    public void increaseCopies(int n) {
        totalCopies += n;
        availableCopies += n;
    }

    public boolean issueOne() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false;
    }

    public void returnOne() {
        if (availableCopies < totalCopies) availableCopies++;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s — %s (Available: %d/%d)", id, title, author, availableCopies, totalCopies);
    }
}
