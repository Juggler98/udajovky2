package tests;

import universalTree.TreeKey;

public class TestClass implements Comparable<TestClass>, TreeKey<Integer> {

    private final Integer kluc;

    TestClass(Integer kluc) {
        this.kluc = kluc;
    }

    @Override
    public int compareTo(TestClass o) {
        return Integer.compare(this.kluc, o.kluc);
    }

    @Override
    public Integer getKey() {
        return this.kluc;
    }

}
