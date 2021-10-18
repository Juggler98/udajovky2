package dvaTriStrom;

import java.util.ArrayList;
import java.util.HashMap;

public class TTTree<K extends Comparable<K>, T extends Comparable<T>> {

    private TTTreeNode<K, T> root;
    private int size = 0;
    private int height = 0;

    public TTTree() {

    }

    public void add(K key, T data) {
        TTTreeNode<K, T> newItem = new TTTreeNode<>(key, data);
        if (!tryToAdd(newItem)) {
            System.out.println("ERROR: Prvok sa nepodarilo vlozit");
        } else {
            ++size;
            //++height;
        }
    }

    private boolean tryToAdd(TTTreeNode<K, T> node) {
        if (this.root == null) {
            this.root = node;
            ++height;
            return true;
        }

        HashMap<String, TTTreeNode<K, T>> leafParentMap = this.findLeaf(node.getKeyL());
        TTTreeNode<K, T> leaf = leafParentMap.get("leaf");

        if (leaf == null)
            return false;

        while (true) {
            if (!leaf.isThreeNode()) {
                if (leaf.getKeyL().compareTo(node.getKeyL()) < 0) {
                    leaf.setKeyR(node.getKeyL());
                    leaf.setDataR(node.getDataL());
                } else {
                    K tempLeftKey = leaf.getKeyL();
                    T tempLeftData = leaf.getDataL();
                    leaf.setKeyL(node.getKeyL());
                    leaf.setDataL(node.getDataL());
                    leaf.setKeyR(tempLeftKey);
                    leaf.setDataR(tempLeftData);
                }
                //leaf.setParent(leafParentMap.get("parent"));
                return true;
            }
            TTTreeNode<K, T> min;
            TTTreeNode<K, T> max;
            TTTreeNode<K, T> middle;
            if (node.getKeyL().compareTo(leaf.getKeyL()) < 0) {
                min = new TTTreeNode<>(node.getKeyL(), node.getDataL());
                max =  new TTTreeNode<>(leaf.getKeyR(), leaf.getDataR());
                middle = new TTTreeNode<>(leaf.getKeyL(), leaf.getDataL());
                min.setLeftSon(leaf.getLeftSon());
            } else if (node.getKeyL().compareTo(leaf.getKeyR()) > 0) {
                leaf.setKeyR(node.getKeyL());
                leaf.setDataR(node.getDataL());
                min =  new TTTreeNode<>(leaf.getKeyL(), leaf.getDataL());
                max = new TTTreeNode<>(node.getKeyL(), node.getDataL());
                middle = new TTTreeNode<K, T>(leaf.getKeyR(), leaf.getDataR());
            } else {
                min =  new TTTreeNode<>(leaf.getKeyL(), leaf.getDataL());
                max = new TTTreeNode<>(leaf.getKeyR(), leaf.getDataR());
                middle = new TTTreeNode<>(node.getKeyL(), node.getDataL());
            }

            if (leaf.hasParent()) {
                TTTreeNode<K, T> leafParent = leaf.getParent();
                if (!leafParent.isThreeNode()) {
                    if (leafParent.getKeyL().compareTo(middle.getKeyL()) < 0) {
                        leafParent.setKeyR(middle.getKeyL());
                        leafParent.setDataR(middle.getDataL());
                        min.setParent(leafParent);
                        max.setParent(leafParent);
                        leafParent.setMiddleSon(min);
                        leafParent.setRightSon(max);
                    } else {
                        K tempLeftKey = leafParent.getKeyL();
                        T tempLeftData = leafParent.getDataL();
                        leafParent.setKeyL(middle.getKeyL());
                        leafParent.setDataL(middle.getDataL());
                        leafParent.setKeyR(tempLeftKey);
                        leafParent.setDataR(tempLeftData);
                        min.setParent(leafParent);
                        max.setParent(leafParent);
                        leafParent.setLeftSon(min);
                        leafParent.setMiddleSon(max);
                    }
                    return true;
                } else {
                    min.setParent(leafParent);
                    max.setParent(leafParent);
                    leafParent.setLeftSon(min);
                    leafParent.setRightSon(max);
                    leaf = leafParent;
                    node = middle;
                }
            } else {
                TTTreeNode<K, T> newRoot = new TTTreeNode<>(middle.getKeyL(), middle.getDataL());
                newRoot.setLeftSon(new TTTreeNode<>(root.getKeyL(), root.getDataL()));
                newRoot.setRightSon(new TTTreeNode<>(root.getKeyR(), root.getDataR()));
                newRoot.getLeftSon().setParent(newRoot);
                newRoot.getRightSon().setParent(newRoot);
                this.root = newRoot;
                ++height;
                return true;
            }
        }
    }

    private ArrayList<TTTreeNode<K, T>> getALl() {
        return null;
    }

    private HashMap<String, TTTreeNode<K, T>> findLeaf(K key) {
        HashMap<String, TTTreeNode<K, T>> leafParent = new HashMap<>();
        TTTreeNode<K, T> leaf = this.root;
        TTTreeNode<K, T> parent = leaf;
        for (int i = 0; i < this.height - 1; i++) {
            parent = leaf;
            if (leaf.isThreeNode()) {
                if (key.compareTo(leaf.getKeyL()) < 0) {
                    if (leaf.hasLeftSon())
                        leaf = leaf.getLeftSon();
                    else
                        break;
                } else if (key.compareTo(leaf.getKeyR()) > 0) {
                    if (leaf.hasRightSon())
                        leaf = leaf.getRightSon();
                    else
                        break;
                } else {
                    if (leaf.hasMiddleSon())
                        leaf = leaf.getMiddleSon();
                    else
                        break;
                }
            } else {
                if (key.compareTo(leaf.getKeyL()) < 0) {
                    if (leaf.hasLeftSon())
                        leaf = leaf.getLeftSon();
                    else
                        break;
                } else {
                    if (leaf.hasRightSon())
                        leaf = leaf.getRightSon();
                    else
                        break;
                }
            }
        }
        if (key != leaf.getKeyL() && leaf.getKeyR() != key) {
            leafParent.put("leaf", leaf);
        }
        if (leaf != parent) {
            leafParent.put("parent", parent);
        }
        //return (key != leaf.getKeyL() && leaf.getKeyR() != key) ? leaf : null;
        return leafParent;
    }

    private TTTreeNode<K, T> findParent(K key) {
        TTTreeNode<K, T> result = this.root;
        if (result == null) {
            return null;
        }
        TTTreeNode<K, T> parent = result;
        while (key != result.getKeyL() && result.getKeyR() != key) {
            parent = result;
            if (result.isThreeNode()) {
                if (key.compareTo(result.getKeyL()) == 0 || key.compareTo(result.getKeyR()) == 0) {
                    break;
                }
                if (key.compareTo(result.getKeyL()) < 0) {
                    if (result.hasLeftSon())
                        result = result.getLeftSon();
                    else
                        break;
                } else if (key.compareTo(result.getKeyR()) > 0) {
                    if (result.hasRightSon())
                        result = result.getRightSon();
                    else
                        break;
                } else {
                    if (result.hasMiddleSon())
                        result = result.getMiddleSon();
                    else
                        break;
                }
            } else {
                if (key.compareTo(result.getKeyL()) < 0) {
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
        }
        return (key == result.getKeyL() || result.getKeyR() == key) && parent != result ? parent : null;
    }

    public TTTreeNode<K, T> search(K key) {
        TTTreeNode<K, T> result = this.root;
        if (result == null) {
            return null;
        }
        while (key != result.getKeyL() && result.getKeyR() != key) {
            if (result.isThreeNode()) {
                if (key.compareTo(result.getKeyL()) == 0 || key.compareTo(result.getKeyR()) == 0) {
                    break;
                }
                if (key.compareTo(result.getKeyL()) < 0) {
                    if (result.hasLeftSon())
                        result = result.getLeftSon();
                    else
                        break;
                } else if (key.compareTo(result.getKeyR()) > 0) {
                    if (result.hasRightSon())
                        result = result.getRightSon();
                    else
                        break;
                } else {
                    if (result.hasMiddleSon())
                        result = result.getMiddleSon();
                    else
                        break;
                }
            } else {
                if (key.compareTo(result.getKeyL()) < 0) {
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
        }
        return (key == result.getKeyL() || result.getKeyR() == key) ? result : null;
    }

    public TTTreeNode<K, T> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    //    public TreeNode search(TreeNode node) {
//        return null;
//    }

    public T remove(K key) {
        TTTreeNode<K, T> node = search(key);
        if (node == null) {
            return null;
        }
        if (!extractNode(node)) {
            return null;
        }
        return node.getData();
    }

    private boolean extractNode(TTTreeNode<K, T> node) {
        TTTreeNode<K, T> parent = node.getParent();
        if (parent == null) {
            this.root = null;
            --size;
            return true;
        }
        if (parent.getKey().compareTo(node.getKey()) < 0) {
            parent.setRightSon(node);
        } else {
            parent.setLeftSon(node);
            --size;
        }
        return true;
    }

    public int getHeight() {
        return height;
    }
}
