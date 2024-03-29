package tests;

import twoThreeTree.TTTree;
import twoThreeTree.TTTreeNode;

import java.util.ArrayList;
import java.util.Random;

public class TestGenerator {

//    private int operationCount;
//
//    public OperationGenerator(int operationCount) {
//        this.operationCount = operationCount;
//    }
//
//    public Operation getOperation() {
//        Random random = new Random();
//        return Operation.values()[random.nextInt(Operation.values().length)];
//    }
//
//    public int getOperationCount() {
//        return this.operationCount;
//    }

    public void runTests() {
        int testCount = 1;
        Random random = new Random();
        Random random1 = new Random();
        for (int j = 0; j < testCount; j++) {
            System.out.println("--------------------------NEW-TEST-------------------------------------");
            int operationCount = 10000;
            int addCount = 0;
            int removeCount = 0;
            int addNotPossible = 0;
            int removeNotPossible = 0;
            //random.setSeed(3);
            //random1.setSeed(2);
            ArrayList<Integer> testArrayList = new ArrayList<>();
            TTTree<Integer, TestClass> testTree = new TTTree<>();

            boolean goRandom = true;

            TestClass[] testNumbers = new TestClass[20];
            if (!goRandom) {
                for (int i = 0; i < testNumbers.length; i++) {
                    testNumbers[i] = new TestClass(i);
                }
            }

            int addPercentage = 99;
            int removePercentage = 100 - addPercentage;
            int randomNumberBound = 100;
            if (goRandom) {
                for (int i = 0; i < operationCount; i++) {
                    if (i % 10000 == 0) {
                        System.out.println(i + " operation");
                    }
                    TestClass testClass = new TestClass((Integer) random.nextInt(randomNumberBound));
                    int randomOperation = random1.nextInt(100);
                    if (randomOperation < addPercentage) {
                        System.out.println("ADD: " + testClass.getKey());
                        addCount++;
                        boolean treeNotAdd = testTree.add(testClass);
                        if (!treeNotAdd) {
                            addNotPossible++;
                        }
                        boolean arrayListNotAdd = !testArrayList.contains(testClass.getKey());
                        if (arrayListNotAdd) {
                            testArrayList.add(testClass.getKey());
                        }
                        if (treeNotAdd && !arrayListNotAdd) {
                            System.out.println(testClass.getKey());
                            System.out.println("-------Test-Problem------");
                        }
                    } else {
                        if (testTree.getSize() > 0) {
                            removeCount++;
                            int keyToDelete = testArrayList.get(random.nextInt(testArrayList.size()));
                            System.out.println("Remove: " + keyToDelete);
                            if (testTree.remove(keyToDelete) == null) {
                                removeNotPossible++;
                            }
                            if (testArrayList.contains(keyToDelete))
                                testArrayList.remove((Integer) keyToDelete);
                        } else {
                            System.out.println("Empty tree");
                        }
                    }
                }

                System.out.println("---------NUMBERS-IN-ARRAYLIST----------");
                for (Integer i : testArrayList) {
                    System.out.println(i);
                }
            }

//            System.out.println("-----------PREORDER------------");
//            testTree.preorder((TTTreeNode<Integer, tests.TestClass>) testTree.getRoot());
//
//            testTree.add(testNumbers[0]);
//            System.out.println("----------TREE-------------");
//            testTree.preorder((TTTreeNode<Integer, tests.TestClass>) testTree.getRoot());
//
//            testTree.remove(3);
//            System.out.println("----------TREE-------------");
//            testTree.preorder((TTTreeNode<Integer, tests.TestClass>) testTree.getRoot());
//
//            testTree.remove(8);
//            tests.TestClass t = new tests.TestClass(3);
//            testTree.add(t);
//
//            System.out.println(((TTTreeNode<?, ?>) testTree.getRoot()).hasParent());
//
//            System.out.println("-------After-change-------");
//
//            testTree.preorder((TTTreeNode<Integer, tests.TestClass>) testTree.getRoot());

            System.out.println("-----------PREORDER------------");
            testTree.preorder((TTTreeNode<Integer, TestClass>) testTree.getRoot());

            System.out.println("-----------INORDER------------");
            testTree.inOrderRecursive((TTTreeNode<Integer, TestClass>) testTree.getRoot());

            System.out.println("---------LEAF-DEEP----------");
            testTree.deepOfLeaf((TTTreeNode<Integer, TestClass>) testTree.getRoot());

            System.out.println();
            System.out.println("addCount: " + addCount);
            System.out.println("notAdded: " + addNotPossible);
            System.out.println("removeCount: " + removeCount);
            System.out.println("notRemove: " + removeNotPossible);
            System.out.println();
            System.out.println("Tree size: " + testTree.getSize());
            System.out.println("ArrayList size " + testArrayList.size());
            System.out.println("Tree height: " + testTree.getHeight());

            System.out.println("Inorder vsetky hodnoty:");
            ArrayList<TestClass> hodnoty = testTree.getInOrderData();
            for (TestClass testClass : hodnoty) {
                System.out.println(testClass.getKey());
            }
            System.out.println("Inorder interval:");
            ArrayList<TestClass> hodnotyInterval = testTree.getIntervalData(50, 55);
            for (TestClass testClass : hodnotyInterval) {
                System.out.println(testClass.getKey());
            }

        }
    }


}
