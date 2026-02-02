import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private long price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public Book() {}
    @Override
    public String toString() {
        return "Book {" +
                "Ma sach=" + id +
                ", Ten='" + title + '\'' +
                ", Tac gia='" + author + '\'' +
                ", Gia=" + price +
                '}';
    }
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma sach: ");
        id = Integer.parseInt(sc.nextLine());
        System.out.println("Nhap ten sach: ");
        title = sc.nextLine();
        System.out.println("Nhap tac gia: ");
        author = sc.nextLine();
        System.out.println("Nhap gia: ");
        price = sc.nextLong();
    }
    public void output(){
        var msg = """
                BOOK: id = %d, title = %s, author = %s, price = %d """.formatted(id, title, author, price);
        System.out.println(msg);
    }

}
