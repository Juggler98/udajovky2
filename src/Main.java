import binarnyStrom.BSTree;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        Osoba osobaTest = new Osoba("adam", "Beliansky", new Date(System.currentTimeMillis()), "9808281500");
//        Osoba[] osoby = new Osoba[5];
//        BinarnyStrom.BSTree<String, Osoba> personTree = new BinarnyStrom.BSTree<>();
//        for (int i = 0; i < 5; i++) {
//            osoby[i] = new Osoba("adam", "Beliansky", new Date(System.currentTimeMillis()), "980828150" + i);
//            osoby[i] = new Osoba("adam", "Beliansky", new Date(System.currentTimeMillis()), "98082815" + i);
//            personTree.add(osoby[i].getRodCislo(), osoby[i]);
//        }
//        PcrTest test = new PcrTest(new Date(System.currentTimeMillis()), "9808281500", 5, 5, 5, false, "A");
//        //personTree.add(osobaTest.getRodCislo(), osobaTest);
//        //personTree.add(osobaTest.getRodCislo(), osobaTest);
//        System.out.println(personTree.getRoot().getData().getRodCislo());
//        System.out.println(personTree.getRoot().getRightSon().getData().getRodCislo());
//        System.out.println(personTree.getRoot().getRightSon().getRightSon().getData().getRodCislo());
//        System.out.println(personTree.getRoot().getRightSon().getRightSon().getRightSon().getData().getRodCislo());
        Random random = new Random();
        TestClass[] tests = new TestClass[20];
        BSTree<Integer, TestClass> testTree = new BSTree<>();
        for (int i = 0; i < tests.length; i++) {
            TestClass pom = new TestClass(random.nextInt(1000));
            testTree.add(pom.getKluc(), pom);
            System.out.println(pom.getKluc());
        }
        System.out.println();
        System.out.println(testTree.getRoot().getData().getKluc() + " root");
        System.out.println(testTree.getRoot().getLeftSon().getData().getKluc() + " root leftSoon");
        System.out.println(testTree.getRoot().getLeftSon().getLeftSon().getData().getKluc() + " root-leftSoon-leftSoon");
        System.out.println(testTree.getRoot().getLeftSon().getRightSon().getData().getKluc() + " root-leftSoon-rightSoon");
        System.out.println(testTree.getRoot().getRightSon().getData().getKluc() + " root-rightSoon");
        System.out.println(testTree.getRoot().getRightSon().getRightSon().getData().getKluc() +" root-rightSoon-right");
        System.out.println(testTree.getRoot().getRightSon().getLeftSon().getData().getKluc()+" root-rightSoon-left");
    }

}
