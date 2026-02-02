import java.time.LocalDate;

public class XeTai extends Xe{
    private int trongTai;

    public XeTai(LocalDate ngaySanXuat, String bienSo, int trongTai) {
        super(ngaySanXuat, bienSo);
        this.trongTai = trongTai;
    }

    public int getTrongTai() {
        return trongTai;
    }
    @Override
    public String toString(){
        return "Xe Tai [" + super.toString() + ", Trong tai: " + trongTai + "]";
    }
    public void setTrongTai(int trongTai) {
        this.trongTai = trongTai;
    }
}
