import java.time.LocalDate;

public abstract class Xe {
    protected LocalDate ngaySanXuat;
    protected String bienSo;

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }
    @Override
    public String toString(){
        return "Bien so: " + bienSo + ", Ngay SX: " + ngaySanXuat;
    }
    public Xe(LocalDate ngaySanXuat, String bienSo) {
        this.ngaySanXuat = ngaySanXuat;
        this.bienSo = bienSo;
    }
}
