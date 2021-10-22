import tests.TestGenerator;

public class Main {

    public static void main(String[] args) {
//        Models.Osoba osobaTest = new Models.Osoba("adam", "Beliansky", new Date(System.currentTimeMillis()), "9808281500");
//        Models.Osoba[] osoby = new Models.Osoba[5];
//        BinarnyStrom.BSTree<String, Models.Osoba> personTree = new BinarnyStrom.BSTree<>();
//        for (int i = 0; i < 5; i++) {
//            osoby[i] = new Models.Osoba("adam", "Beliansky", new Date(System.currentTimeMillis()), "980828150" + i);
//            osoby[i] = new Models.Osoba("adam", "Beliansky", new Date(System.currentTimeMillis()), "98082815" + i);
//            personTree.add(osoby[i].getRodCislo(), osoby[i]);
//        }
//        Models.PcrTest test = new Models.PcrTest(new Date(System.currentTimeMillis()), "9808281500", 5, 5, 5, false, "A");
//        //personTree.add(osobaTest.getRodCislo(), osobaTest);
//        //personTree.add(osobaTest.getRodCislo(), osobaTest);
//        System.out.println(personTree.getRoot().getData().getRodCislo());
//        System.out.println(personTree.getRoot().getRightSon().getData().getRodCislo());
//        System.out.println(personTree.getRoot().getRightSon().getRightSon().getData().getRodCislo());
//        System.out.println(personTree.getRoot().getRightSon().getRightSon().getRightSon().getData().getRodCislo());


        TestGenerator test = new TestGenerator();
        test.runTests();
    }
}
