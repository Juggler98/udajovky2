import java.util.Date;
import java.util.UUID;

public class PcrTest {

    private Date datum;
    private String rodCisloPacienta;
    private String kodTestu;
    private int kodPracoviska;
    private int kodOkresu;
    private int kodKraja;
    private boolean vysledok;
    private String poznamka;

    public PcrTest(Date datum, String rodCisloPacienta, int kodPracoviska, int kodOkresu, int kodKraja, boolean vysledok, String poznamka) {
        this.datum = datum;
        this.rodCisloPacienta = rodCisloPacienta;
        this.kodTestu = UUID.randomUUID().toString();
        this.kodPracoviska = kodPracoviska;
        this.kodOkresu = kodOkresu;
        this.kodKraja = kodKraja;
        this.vysledok = vysledok;
        this.poznamka = poznamka;
        System.out.println(kodTestu);
    }

    public Date getDatum() {
        return datum;
    }

    public String getRodCisloPacienta() {
        return rodCisloPacienta;
    }

    public String getKodTestu() {
        return kodTestu;
    }

    public int getKodPracoviska() {
        return kodPracoviska;
    }

    public int getKodOkresu() {
        return kodOkresu;
    }

    public int getKodKraja() {
        return kodKraja;
    }

    public boolean isVysledok() {
        return vysledok;
    }

    public String getPoznamka() {
        return poznamka;
    }

    public void setVysledok(boolean vysledok) {
        this.vysledok = vysledok;
    }
}
