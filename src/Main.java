import binarnyStrom.BSTree;
import dvaTriStrom.TTTree;

import java.util.ArrayList;
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

//        Random random = new Random();
//        TestClass[] tests = new TestClass[20];
//        BSTree<Integer, TestClass> testTree = new BSTree<>();
//        for (int i = 0; i < tests.length; i++) {
//            TestClass pom = new TestClass(random.nextInt(1000));
//            testTree.add(pom.getKluc(), pom);
//            System.out.println(pom.getKluc());
//        }
//        System.out.println();
//        System.out.println(testTree.getRoot().getData().getKluc() + " root");
//        System.out.println(testTree.getRoot().getLeftSon().getData().getKluc() + " root leftSoon");
//        System.out.println(testTree.getRoot().getLeftSon().getLeftSon().getData().getKluc() + " root-leftSoon-leftSoon");
//        System.out.println(testTree.getRoot().getLeftSon().getRightSon().getData().getKluc() + " root-leftSoon-rightSoon");
//        System.out.println(testTree.getRoot().getRightSon().getData().getKluc() + " root-rightSoon");
//        System.out.println(testTree.getRoot().getRightSon().getRightSon().getData().getKluc() +" root-rightSoon-right");
//        System.out.println(testTree.getRoot().getRightSon().getLeftSon().getData().getKluc()+" root-rightSoon-left");


        //Random random = new Random();
        //random.setSeed(50);
        //TTTree<Integer, TestClass> testTree = new TTTree<>();


        //for (int i = 0; i < tests.length; i++) {
            //TestClass pom = new TestClass(random.nextInt(1000));
            // testTree.add(pom.getKluc(), pom);
            //testTree.add(tests[i].getKluc(), tests[i]);
            //System.out.println(tests[i].getKluc());
        //}
        //System.out.println();

        //testTree.remove(20);


        //System.out.println(testTree.inOrder(testTree.getRoot()).getDataL().getKluc());

        //OperationGenerator operationGenerator = new OperationGenerator(5000);

        int operationCount = 100;
        int addCount = 0;
        int removeCount = 0;
        int addNotPossible = 0;
        int removeNotPossible = 0;
        Random random = new Random();
        Random random1 = new Random();
        //random.setSeed(50);
        //random1.setSeed(50);
        ArrayList<Integer> testArrayList = new ArrayList<>();
        TTTree<Integer, TestClass> testTree2 = new TTTree<>();

        TestClass[] tests = new TestClass[3];

        tests[0] = new TestClass(7);
        tests[1] = new TestClass(8);
        tests[2] = new TestClass(1);
//        tests[3] = new TestClass(16);
//        tests[4] = new TestClass(8);
//        tests[5] = new TestClass(0);
//        tests[6] = new TestClass(12);
//        tests[7] = new TestClass(28);
//        tests[8] = new TestClass(7);
//        tests[9] = new TestClass(100);
//        tests[10] = new TestClass(150);

        for (int i = 0; i <tests.length; i++) {
            //testTree2.add(tests[i].getKluc(), tests[i]);
            //System.out.println(tests[i].getKluc());
        }

        for (int i = 0; i < operationCount; i++) {
            TestClass pom = new TestClass(random.nextInt(100));
            int randomNumber = random1.nextInt(100) + 1;
            if (randomNumber <= 50) {
                System.out.println("ADD: " + pom.getKluc());
                addCount++;
                boolean treeNotAdd = testTree2.add(pom.getKluc(), pom);
                if (!treeNotAdd) {
                    addNotPossible++;
                }
                boolean arrayListNotAdd = !testArrayList.contains(pom.getKluc());
                if (arrayListNotAdd) {
                    testArrayList.add(pom.getKluc());
                }
                if (treeNotAdd && !arrayListNotAdd) {
                    System.out.println(pom.getKluc());
                    System.out.println("----------Problem------");
                }
            } else if (randomNumber >= 50) {
                System.out.println("Remove: " + pom.getKluc());
                removeCount++;
                if (testTree2.remove(pom.getKluc()) == null) {
                    removeNotPossible++;
                }
                if (testArrayList.contains(pom.getKluc()))
                    testArrayList.remove((Integer) pom.getKluc());
//                boolean found = false;
//                int index = 0;
//                for (TestClass t : testArrayList) {
//                    if (t.getKluc() == pom.getKluc()) {
//                        found = true;
//                        break;
//                    }
//                    index++;
//                }
//                if (found) {
//                    testArrayList.remove(index);
//                }
//                if (testArrayList.contains(pom))
//                    testArrayList.remove(pom.getKluc());
            }
        }

        for (int i = 0; i < testArrayList.size(); i++) {
            System.out.println(testArrayList.get(i));
        }

        System.out.println("-----------------------");

        testTree2.preorder(testTree2.getRoot());

        //System.out.println("-----------------------");

        //testTree2.remove(36);
       // testTree2.remove(17);
        //testTree2.remove(12);
        //testTree2.remove(8);
        //testTree2.remove(16);
        //testTree2.remove(28);
        //testTree2.remove(1);
        //testTree2.remove(1);

        //testTree2.remove(7);
        //testTree2.remove(0);
        //System.out.println("----------------" + testTree2.getRoot().getRightSon().getParent().getKeyL());
        //testTree2.remove(16);
        //testTree2.remove(150);
        //testTree2.remove(36);
        //testTree2.remove(100);
        //testTree2.remove(100);

        TestClass t = new TestClass(3);
        //testTree2.add(t.getKluc(), t);
        //testTree2.add(t.getKluc(), t);
        //testTree2.remove(15);


        //System.out.println(testTree2.getRoot().hasParent());

        System.out.println("-------After-change-------");

        testTree2.preorder(testTree2.getRoot());



        System.out.println("addCount: " + addCount);
        System.out.println("addNotPossible: " + addNotPossible);
        System.out.println("removeCount: " + removeCount);
        System.out.println("removeNotPossible: " + removeNotPossible);
        System.out.println();
        System.out.println("Tree size: " + testTree2.getSize());
        System.out.println("arrayList size " + testArrayList.size());
        System.out.println("Tree height: " + testTree2.getHeight());


        //TestClass pom = new TestClass(9999);
        //testTree.add(pom.getKluc(), pom);
        //System.out.println(testTree.search(9999).getDataL().getKluc());
//        System.out.println();
//        System.out.println(testTree.getRoot().getDataL().getKluc());
////        //System.out.println(testTree.getRoot().getDataR().getKluc());
//        testTree.getRoot().vypis();
////
//        System.out.println(testTree.getRoot().getLeftSon().getDataL().getKluc());
//        testTree.getRoot().getLeftSon().vypis();
//
////        System.out.println(testTree.getRoot().getMiddleSon().getDataL().getKluc());
////        testTree.getRoot().getMiddleSon().vypis();
//
//        System.out.println("Problem part: ");
//        System.out.println(testTree.getRoot().getRightSon().getDataL().getKluc());
//        System.out.println(testTree.getRoot().getRightSon().getDataR().getKluc());
//        testTree.getRoot().getRightSon().vypis();
//
////        System.out.println(testTree.getRoot().getRightSon().getDataR().getKluc());
////        testTree.getRoot().getRightSon().vypis();
//
//        System.out.println(testTree.getRoot().getLeftSon().getLeftSon().getDataL().getKluc());
//        testTree.getRoot().getLeftSon().getLeftSon().vypis();
//
//        //System.out.println(testTree.getRoot().getLeftSon().getRightSon().getDataR().getKluc());
//        System.out.println(testTree.getRoot().getLeftSon().getRightSon().getDataL().getKluc());
//        testTree.getRoot().getLeftSon().getRightSon().vypis();
//
//        System.out.println(testTree.getRoot().getRightSon().getLeftSon().getDataL().getKluc());
//        testTree.getRoot().getRightSon().getLeftSon().vypis();
//
//        System.out.println(testTree.getRoot().getRightSon().getRightSon().getDataL().getKluc());
//        testTree.getRoot().getRightSon().getRightSon().vypis();


//        System.out.println(testTree.getRoot().getRightSon().getDataR().getKluc());
//        testTree.getRoot().getRightSon().vypis();

//        System.out.println(testTree.getRoot().getDataL().getKluc());
//        System.out.println(testTree.getRoot().getLeftSon().getDataL().getKluc());
//        System.out.println(testTree.getRoot().getLeftSon().getDataR().getKluc());
//        System.out.println(testTree.getRoot().getLeftSon().getLeftSon().getDataL().getKluc());
//        System.out.println(testTree.getRoot().getLeftSon().getLeftSon().getDataR().getKluc());
//        System.out.println(testTree.getRoot().getLeftSon().getMiddleSon().getDataL().getKluc());
//        System.out.println(testTree.getRoot().getLeftSon().getRightSon().getDataL().getKluc());
//        System.out.println();
//        System.out.println(testTree.getRoot().getRightSon().getDataL().getKluc());
//        System.out.println(testTree.getRoot().getRightSon().getLeftSon().getDataL().getKluc());
//        System.out.println(testTree.getRoot().getRightSon().getRightSon().getDataL().getKluc());

//        System.out.println("Tree size: " + testTree.getSize());
//        System.out.println("Tree height: " + testTree.getHeight());

//        System.out.println(testTree.getRoot().getDataR().getKluc() + " root R");
//        System.out.println(testTree.getRoot().getLeftSon().getDataL().getKluc() + " root leftSoon L");
//        System.out.println(testTree.getRoot().getLeftSon().getDataR().getKluc() + " root leftSoon R");
//        System.out.println(testTree.getRoot().getLeftSon().getLeftSon().getDataL().getKluc() + " root-leftSoon-leftSoon L");
//        System.out.println(testTree.getRoot().getLeftSon().getLeftSon().getDataR().getKluc() + " root-leftSoon-leftSoon R");
//        System.out.println(testTree.getRoot().getLeftSon().getRightSon().getDataL().getKluc() + " root-leftSoon-rightSoon L");
//        System.out.println(testTree.getRoot().getLeftSon().getRightSon().getDataR().getKluc() + " root-leftSoon-rightSoon R");
//        System.out.println(testTree.getRoot().getRightSon().getDataL().getKluc() + " root-rightSoon L");
//        System.out.println(testTree.getRoot().getRightSon().getDataR().getKluc() + " root-rightSoon R");
//        System.out.println(testTree.getRoot().getRightSon().getRightSon().getDataL().getKluc() +" root-rightSoon-right L");
//        System.out.println(testTree.getRoot().getRightSon().getRightSon().getDataR().getKluc() +" root-rightSoon-right R");
//        System.out.println(testTree.getRoot().getRightSon().getLeftSon().getDataL().getKluc()+" root-rightSoon-left L");
//        System.out.println(testTree.getRoot().getRightSon().getLeftSon().getDataR().getKluc()+" root-rightSoon-left R");
    }

}
