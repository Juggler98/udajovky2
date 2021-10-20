import dvaTriStrom.TTTreeKey;

public class TestClass implements Comparable<TestClass>, TTTreeKey<Integer> {

    private int kluc;

    TestClass(int kluc) {
        this.kluc = kluc;
    }

    @Override
    public int compareTo(TestClass o) {
        return Integer.compare(this.kluc, o.kluc);
    }

    @Override
    public Integer getKey() {
        return (Integer) this.kluc;
    }

    public int getKluc() {
        return this.kluc;
    }

}
