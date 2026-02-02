import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class QuanLyXe {
    // Danh sách lưu trữ đa hình (chứa cả XeOto và XeTai)
    static List<Xe> listXe = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        khoiTaoDuLieuMau();

        int chon;
        do {
            System.out.println("\n--- MENU QUAN LY XE ---");
            System.out.println("1. Them Xe Oto");
            System.out.println("2. Them Xe Tai");
            System.out.println("3. Hien thi tat ca");
            System.out.println("4. Luu file (dulieu.txt)");
            System.out.println("5. Tim Oto nhieu ghe nhat");
            System.out.println("6. Sap xep Xe Tai (trong tai tang dan)");
            System.out.println("7. Tim bien so dep (Tu quy trong 5 so cuoi)");
            System.out.println("0. Thoat");
            System.out.print("Moi chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                chon = -1;
            }

            switch (chon) {
                case 1: themXeOto(); break;
                case 2: themXeTai(); break;
                case 3: listXe.forEach(System.out::println); break;
                case 4: luuFile(); break;
                case 5: timOtoNhieuGheNhat(); break;
                case 6: sapXepXeTai(); break;
                case 7: timBienSoDep(); break;
                case 0: System.out.println("Ket thuc chuong trinh!"); break;
                default: System.out.println("Chon sai, vui long chon lai!");
            }
        } while (chon != 0);
    }



    public static void themXeOto() {
        try {
            System.out.println("--- Nhap thong tin Xe Oto ---");
            System.out.print("Nhap bien so: ");
            String bs = sc.nextLine();

            System.out.print("Nhap ngay SX (dd/MM/yyyy): ");
            LocalDate ngay = LocalDate.parse(sc.nextLine(), dtf);

            System.out.print("So ghe: ");
            int ghe = Integer.parseInt(sc.nextLine());

            System.out.print("Kinh doanh van tai (true/false): ");
            boolean kd = Boolean.parseBoolean(sc.nextLine());

            // LƯU Ý: Thứ tự tham số phải khớp với file XeOto bạn gửi
            // (int soGhe, boolean kinhDoanh, LocalDate ngaySanXuat, String bienSo)
            XeOto otoMoi = new XeOto(ghe, kd, ngay, bs);
            listXe.add(otoMoi);
            System.out.println("Them thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi nhap lieu: " + e.getMessage());
        }
    }

    public static void themXeTai() {
        try {
            System.out.println("--- Nhap thong tin Xe Tai ---");
            System.out.print("Nhap bien so: ");
            String bs = sc.nextLine();

            System.out.print("Nhap ngay SX (dd/MM/yyyy): ");
            LocalDate ngay = LocalDate.parse(sc.nextLine(), dtf);

            System.out.print("Trong tai (tan): ");
            int tai = Integer.parseInt(sc.nextLine());

            // LƯU Ý: Thứ tự tham số khớp với file XeTai
            // (LocalDate ngaySanXuat, String bienSo, int trongTai)
            XeTai taiMoi = new XeTai(ngay, bs, tai);
            listXe.add(taiMoi);
            System.out.println("Them thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi nhap lieu: " + e.getMessage());
        }
    }



    // Y/c 5: Tìm xe ô tô có số chỗ ngồi nhiều nhất
    public static void timOtoNhieuGheNhat() {
        System.out.println(">> Ket qua tim kiem:");
        listXe.stream()
                .filter(xe -> xe instanceof XeOto)
                .map(xe -> (XeOto) xe)
                .max(Comparator.comparingInt(XeOto::getSoGhe))
                .ifPresentOrElse(
                        xe -> System.out.println("Xe oto nhieu ghe nhat: " + xe),
                        () -> System.out.println("Khong co xe oto nao trong danh sach")
                );
    }

    // Y/c 6: Sắp xếp xe tải có trọng tải tăng dần
    public static void sapXepXeTai() {
        System.out.println(">> Danh sach xe tai (tang dan theo trong tai):");
        listXe.stream()
                .filter(xe -> xe instanceof XeTai)
                .map(xe -> (XeTai) xe)
                .sorted(Comparator.comparingInt(XeTai::getTrongTai))
                .forEach(System.out::println);
    }

    // Y/c 7: Tìm biển số xe đẹp (Tứ quý trong 5 số cuối)
    public static void timBienSoDep() {
        System.out.println(">> Cac xe co bien so dep:");
        boolean found = false;
        for (Xe x : listXe) {
            String bs = x.getBienSo();
            // Lấy 5 ký tự cuối cùng
            if (bs.length() >= 5) {
                String namSoCuoi = bs.substring(bs.length() - 5);
                if (checkTuQuy(namSoCuoi)) {
                    System.out.println(x);
                    found = true;
                }
            }
        }
        if (!found) System.out.println("Khong tim thay bien so dep nao.");
    }

    // Hàm phụ trợ kiểm tra 4 số giống nhau
    private static boolean checkTuQuy(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        // Nếu có ký tự nào xuất hiện >= 4 lần -> True
        return counts.values().stream().anyMatch(val -> val >= 4);
    }

    //4.
    public static void luuFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dulieuxe.txt"))) {
            for (Xe x : listXe) {
                bw.write(x.toString());
                bw.newLine();
            }
            System.out.println("Da luu vao file 'dulieuxe.txt' thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file.");
        }
    }

    public static void khoiTaoDuLieuMau() {
        // XeOto: (soGhe, kinhDoanh, ngay, bienSo)
        listXe.add(new XeOto(4, false, LocalDate.of(2020, 1, 1), "51A-12345"));
        listXe.add(new XeOto(7, true, LocalDate.of(2022, 5, 20), "30E-99999")); // Biển đẹp

        // XeTai: (ngay, bienSo, trongTai)
        listXe.add(new XeTai(LocalDate.of(2019, 10, 10), "60C-88881", 15)); // Biển đẹp
        listXe.add(new XeTai(LocalDate.of(2021, 3, 15), "60C-22222", 2));   // Biển đẹp
        listXe.add(new XeTai(LocalDate.of(2023, 1, 1), "29C-56789", 5));
    }
}