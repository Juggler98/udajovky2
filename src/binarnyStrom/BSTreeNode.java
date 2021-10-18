package binarnyStrom;

public class BSTreeNode<K extends Comparable<K>, T extends Comparable<T>> {

    private BSTreeNode<K, T> parent;
    private BSTreeNode<K, T> leftSon;
    private BSTreeNode<K, T> rightSon;

    private T data;
    private K key;

    BSTreeNode(K key, T data) {
        this.data = data;
        this.key = key;
    }

    public void setParent(BSTreeNode<K, T> parent) {
        this.parent = parent;
    }

    public void setLeftSon(BSTreeNode<K, T> leftSon) {
        this.leftSon = leftSon;
    }

    public void setRightSon(BSTreeNode<K, T> rightSon) {
        this.rightSon = rightSon;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BSTreeNode<K, T> getParent() {
        return parent;
    }

    public BSTreeNode<K, T> getLeftSon() {
        return leftSon;
    }

    public BSTreeNode<K, T> getRightSon() {
        return rightSon;
    }

    public T getData() {
        return data;
    }

    public K getKey() {
        return this.key;
    }

    public boolean hasLeftSon() {
        return this.leftSon != null;
    }

    public boolean hasRightSon() {
        return this.rightSon != null;
    }
}
