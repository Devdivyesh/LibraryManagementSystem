import java.io.Serializable;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String contact;

    public Member(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getContact() { return contact; }

    @Override
    public String toString() {
        return String.format("[%d] %s — %s", id, name, contact);
    }
}
