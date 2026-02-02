//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;


public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        var scanner = new Scanner(System.in);
        var msg = """
                Chuong trinh quan li sach
                 1. Them 1 cuon sach
                 2. Xoa 1 cuon sach
                 3. Thay doi thong tin 1 cuon sach
                 4. Xuat thong tin tat ca cac cuon sach
                 5. Tim kiem "Lap trinh"
                 6. Lay sach toi da theo gia
                 7. Tim kiem sach theo ten tac gia
                 0. Thoat
                 Chon chuc nang:""";
        int choice;
        do {
            System.out.print(msg);
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1 -> {
                    var book = new Book();
                    book.input();
                    books.add(book);
                }
                case 2 -> {
                    System.out.println("Nhap ma sach can xoa: ");
                    var id = Integer.parseInt(scanner.nextLine());
                    books.stream()
                            .filter(book -> book.getId() == id)
                            .findFirst()
                            .ifPresent(books::remove);
                    System.out.println("Da xoa sach co ma " + id);
                }
                case 3 -> {
                    System.out.println("Nhap ma sach");
                    var id = scanner.nextInt();
                    scanner.nextLine();
                    books.stream()
                            .filter(book -> book.getId() == id)
                            .findFirst()
                            .ifPresent(Book::input);
                    System.out.println("Da thay doi thong tin sach ");
                }
                case 4 -> {
                    System.out.println("Thong tin tac ca cac cuon sach ");
                    books.forEach(System.out::println);

                }
                case 5 -> {
                    System.out.println("Thong tin cac sach co chua 'Lap trinh': ");
                    books.stream()
                            .filter(book -> book.getTitle().contains("Lap trinh"))
                            .forEach(Book::output);
                }
                case 6 -> {
                    double priceCondition = 100000;
                    long maxResult = 5;
                    System.out.println("Lay sach toi da theo gia");
                    books.stream()
                            .filter(book -> book.getPrice() > priceCondition)
                            .limit(maxResult)
                            .forEach(Book::output);
                }
                case 7 -> {
                    Set<String> targetAuthors = new HashSet<>();
                    targetAuthors.add("Nguyen Van A");
                    targetAuthors.add("Nguyen Van B");
                    books.stream()
                            .filter(book -> targetAuthors.contains(book.getAuthor()))
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Da thoat");
                default -> throw new IllegalArgumentException();
            }

        }while (choice != 0);
    }
}