public class TestClass implements Comparable<TestClass> {

    private int kluc;

    TestClass(int kluc) {
        this.kluc = kluc;
    }

    @Override
    public int compareTo(TestClass o) {
        return Integer.compare(this.kluc, o.kluc);
    }

    public int getKluc() {
        return this.kluc;
    }

}
