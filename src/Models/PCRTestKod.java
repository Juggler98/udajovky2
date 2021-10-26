package Models;

import universalTree.TreeKey;

public class PCRTestKod implements Comparable<PCRTestKod>, TreeKey<String>  {

    private PCRTest data;

    public PCRTestKod(PCRTest data) {
        this.data = data;
    }

    public PCRTest getData() {
        return data;
    }

    @Override
    public int compareTo(PCRTestKod o) {
        return data.kodTestu.compareTo(o.data.kodTestu);
    }

    @Override
    public String getKey() {
        return this.data.kodTestu;
    }

}
