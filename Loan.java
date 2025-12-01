import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;
    private int bookId;
    private int memberId;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public Loan(int bookId, int memberId, LocalDate issueDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }

    @Override
    public String toString() {
        return String.format("BookID:%d issued to MemberID:%d on %s (due %s)", bookId, memberId, issueDate, dueDate);
    }
}
