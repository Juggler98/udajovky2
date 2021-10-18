package dvaTriStrom;


public class TTTreeNode<K extends Comparable<K>, T extends Comparable<T>> {

    private TTTreeNode<K, T> parent;
    private TTTreeNode<K, T> leftSon;
    private TTTreeNode<K, T> middleSon;
    private TTTreeNode<K, T> rightSon;
    private K keyL;
    private K keyR;

    private T dataL;
    private T dataR;

    TTTreeNode(K key, T data) {
        this.dataL = data;
        this.keyL = key;
    }

    public boolean isThreeNode() {
        return hasKeyR();
    }

    public void vypis() {
        if (hasMiddleSon()) {
            System.out.println("hasMIDDLESon");
        }
        if (hasRightSon()) {
            System.out.println("hasRIGHTSon");
        }
        if (hasLeftSon()) {
            System.out.println("hasLEFTSon");
        }
        if (isThreeNode()) {
            System.out.println("3 NODE");
        } else {
            System.out.println("2 NODE");
        }
        System.out.println();
        if ((hasKeyR() && dataR == null) || (!hasKeyR() && dataR != null)) {
            System.out.println("-------------Error------keyR-or-dataR----------");
        }
        if (keyL == null || dataL == null) {
            System.out.println("-------------Error-------keyL-or-dataL---------");
        }
    }

    public boolean hasKeyR() {
        return keyR != null;
    }

    public void setMiddleSon(TTTreeNode<K, T> middleSon) {
        this.middleSon = middleSon;
    }

    public void setKeyL(K keyL) {
        this.keyL = keyL;
    }

    public void setKeyR(K keyR) {
        this.keyR = keyR;
    }

    public void setDataL(T dataL) {
        this.dataL = dataL;
    }

    public void setDataR(T dataR) {
        this.dataR = dataR;
    }

    public TTTreeNode<K, T> getMiddleSon() {
        return middleSon;
    }

    public K getKeyL() {
        return keyL;
    }

    public K getKeyR() {
        return keyR;
    }

    public T getDataL() {
        return dataL;
    }

    public T getDataR() {
        return dataR;
    }

    public void setParent(TTTreeNode<K, T> parent) {
        this.parent = parent;
    }

    public void setLeftSon(TTTreeNode<K, T> leftSon) {
        this.leftSon = leftSon;
    }

    public void setRightSon(TTTreeNode<K, T> rightSon) {
        this.rightSon = rightSon;
    }


    public TTTreeNode<K, T> getParent() {
        return parent;
    }

    public TTTreeNode<K, T> getLeftSon() {
        return leftSon;
    }

    public TTTreeNode<K, T> getRightSon() {
        return rightSon;
    }


    public boolean hasLeftSon() {
        return this.leftSon != null;
    }

    public boolean hasRightSon() {
        return this.rightSon != null;
    }

    public boolean hasParent() {return this.parent != null;}

    public boolean hasMiddleSon() {
        return this.middleSon != null;
    }

}
