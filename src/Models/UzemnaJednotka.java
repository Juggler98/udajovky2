package Models;

import universalTree.TreeKey;

public abstract class UzemnaJednotka implements Comparable<UzemnaJednotka>, TreeKey<Integer> {

    protected Integer kod;
    protected String nazov;

    UzemnaJednotka(int kod, String nazov) {
        this.kod = kod;
        this.nazov = nazov;
    }

    @Override
    public int compareTo(UzemnaJednotka u) {
        return kod.compareTo(u.kod);
    }

    @Override
    public Integer getKey() {
        return kod;
    }

    public int getKod() {
        return kod;
    }

    public String getNazov() {
        return nazov;
    }
}
