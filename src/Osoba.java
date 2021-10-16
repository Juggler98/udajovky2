import java.util.Date;
import java.lang.Comparable;

public class Osoba implements Comparable<Osoba> {

    private String meno;
    private String priezvisko;
    private Date datumNarodenia;
    private String rodCislo;

    public Osoba(String meno, String priezvisko, Date datumNarodenia, String rodCislo) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.datumNarodenia = datumNarodenia;
        this.rodCislo = rodCislo;
    }

    @Override
    public int compareTo(Osoba o) {
        return 0;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public Date getDatumNarodenia() {
        return datumNarodenia;
    }

    public String getRodCislo() {
        return rodCislo;
    }
}
