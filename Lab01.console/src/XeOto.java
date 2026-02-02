import java.time.LocalDate;

public class XeOto extends Xe{
    private int soGhe;
    private boolean kinhDoanh;

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public boolean isKinhDoanh() {
        return kinhDoanh;
    }

    public void setKinhDoanh(boolean kinhDoanh) {
        this.kinhDoanh = kinhDoanh;
    }
    @Override
    public String toString(){
        return "Oto [" + super.toString() + ", So ghe: " + soGhe + ", Kinh doanh: " + (kinhDoanh ? "Co" : "Khong") + "]";
    }
    public XeOto(int soGhe, boolean kinhDoanh, LocalDate ngaySanXuat, String bienSo) {
        super(ngaySanXuat, bienSo);
        this.soGhe = soGhe;
        this.kinhDoanh = kinhDoanh;
    }

}
