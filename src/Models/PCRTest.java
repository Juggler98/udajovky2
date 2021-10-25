package Models;

import universalTree.TreeKey;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class PCRTest implements Comparable<PCRTest>, TreeKey<String>  {

    private final Date datum;
    private final String rodCisloPacienta;
    private final String kodTestu;
    private final int kodPracoviska;
    private final int kodOkresu;
    private final int kodKraja;
    private boolean vysledok;
    private String poznamka;

    public PCRTest(String rodCisloPacienta, int kodPracoviska, int kodOkresu, int kodKraja, boolean vysledok, String poznamka) {
        this.datum = new Date(System.currentTimeMillis());
        this.rodCisloPacienta = rodCisloPacienta;
//        this.kodTestu = UUID.randomUUID().toString();
        Random random = new Random();
        this.kodTestu = "" + random.nextInt(1000000);
        this.kodPracoviska = kodPracoviska;
        this.kodOkresu = kodOkresu;
        this.kodKraja = kodKraja;
        this.vysledok = vysledok;
        this.poznamka = poznamka;
        System.out.println(kodTestu);
    }

    @Override
    public int compareTo(PCRTest o) {
        return kodTestu.compareTo(o.kodTestu);
    }

    @Override
    public String getKey() {
        return this.kodTestu;
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

    public void setPoznamka(String poznamka) {
        this.poznamka = poznamka;
    }
}
