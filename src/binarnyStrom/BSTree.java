package binarnyStrom;

import java.util.ArrayList;

public class BSTree<K extends Comparable<K>, T extends Comparable<T>> {

    private BSTreeNode<K, T> root;
    private int size = 0;

    public BSTree() {

    }


    public void add(K key, T data) {
        BSTreeNode<K, T> newItem = new BSTreeNode<>(key, data);
        if (!tryToAdd(newItem)) {
            System.out.println("ERROR: Prvok sa nepodarilo vlozit");
        }
    }

    private boolean tryToAdd(BSTreeNode<K, T> node) {
        if (this.root == null) {
            this.root = node;
            ++size;
            return true;
        }

        BSTreeNode<K, T> parent = this.findParent(node.getKey());

        if (parent == null)
            return false;
        if (parent.getData().compareTo(node.getData()) < 0)
            parent.setRightSon(node);
        else
            parent.setLeftSon(node);
        node.setParent(parent);
        ++size;
        return true;
    }

    private ArrayList<BSTreeNode<K, T>> getALl() {
        return null;
    }

    private BSTreeNode<K, T> findParent(K key) {
        BSTreeNode<K, T> result = this.root;
        while (key != result.getKey()) {
            if (key.compareTo(result.getKey()) < 0) {
                if (result.hasLeftSon())
                    result = result.getLeftSon();
                else
                    break;
            } else {
                if (result.hasRightSon())
                    result = result.getRightSon();
                else
                    break;
            }
        }
        return key != result.getKey() ? result : null;
    }

    public BSTreeNode<K, T> search(K key) {
        BSTreeNode<K, T> result = this.root;
        if (result == null) {
            return null;
        }
        while (key != result.getKey()) {
            if (key.compareTo(result.getKey()) != 0) {
                if (result.hasLeftSon())
                    result = result.getLeftSon();
                else
                    break;
            } else {
                if (result.hasRightSon())
                    result = result.getRightSon();
                else
                    break;
            }
        }
        return key == result.getKey() ? result : null;
    }

    public BSTreeNode<K, T> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    //    public TreeNode search(TreeNode node) {
//        return null;
//    }

    public T remove(K key) {
        BSTreeNode<K, T> node = search(key);
        if (node == null) {
            return null;
        }
        if (!extractNode(node)) {
            return null;
        }
        return node.getData();
    }

    private boolean extractNode(BSTreeNode<K, T> node) {
        BSTreeNode<K, T> parent = node.getParent();
        if (parent == null) {
            this.root = null;
            --size;
            return true;
        }
        if (parent.getKey().compareTo(node.getKey()) < 0) {
            parent.setRightSon(node);
        }  else {
            parent.setLeftSon(node);
            --size;
        }
        return true;
    }
}
