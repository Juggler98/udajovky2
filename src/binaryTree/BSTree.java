package binaryTree;

import universalTree.Tree;
import universalTree.TreeKey;

import java.util.ArrayList;

public class BSTree<K extends Comparable<K>, T extends Comparable<T> & TreeKey<K>> extends Tree<K, T> {

    public BSTree() {

    }

    @Override
    public boolean add(T data) {
        BSTreeNode<K, T> newItem = new BSTreeNode<>(data);
        if (!tryToAdd(newItem)) {
            System.out.println("ERROR: Prvok sa nepodarilo vlozit");
            return false;
        }
        return true;
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
        BSTreeNode<K, T> result = (BSTreeNode<K, T>) this.root;
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

    @Override
    public T search(K key) {
        BSTreeNode<K, T> result = (BSTreeNode<K, T>) this.root;
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
        return key == result.getKey() ? result.getData() : null;
    }

    public BSTreeNode<K, T> getNodeToRemove(K key) {
        BSTreeNode<K, T> result = (BSTreeNode<K, T>) this.root;
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

//    public BSTreeNode<K, T> getRoot() {
//        return root;
//    }

//    public int getSize() {
//        return size;
//    }

    //    public dvaTriStrom.TreeNode search(dvaTriStrom.TreeNode node) {
//        return null;
//    }

    public T remove(K key) {
        BSTreeNode<K, T> node = getNodeToRemove(key);
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
