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
            System.out.println("Prvok sa nepodarilo vlozit");
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

        TTTreeNode<K, T> min = null;
        TTTreeNode<K, T> max = null;
        TTTreeNode<K, T> middle = null;
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
            TTTreeNode<K, T> tempMin = min;
            TTTreeNode<K, T> tempMax = max;
            if (node.getKeyL().compareTo(leaf.getKeyL()) < 0) {
                min = new TTTreeNode<>(node.getKeyL(), node.getDataL());
                max = new TTTreeNode<>(leaf.getKeyR(), leaf.getDataR());
                middle = new TTTreeNode<>(leaf.getKeyL(), leaf.getDataL());
                //min.setLeftSon(leaf.getLeftSon());
            } else if (node.getKeyL().compareTo(leaf.getKeyR()) > 0) {
                //leaf.setKeyR(node.getKeyL());
                //leaf.setDataR(node.getDataL());
                min = new TTTreeNode<>(leaf.getKeyL(), leaf.getDataL());
                max = new TTTreeNode<>(node.getKeyL(), node.getDataL());
                middle = new TTTreeNode<K, T>(leaf.getKeyR(), leaf.getDataR());
            } else {
                min = new TTTreeNode<>(leaf.getKeyL(), leaf.getDataL());
                max = new TTTreeNode<>(leaf.getKeyR(), leaf.getDataR());
                middle = new TTTreeNode<>(node.getKeyL(), node.getDataL());
            }
            if (tempMin != null && tempMax != null) {
                if (tempMin.getKeyL().compareTo(leaf.getKeyL()) < 0) {
                    min.setLeftSon(tempMin);
                    min.setRightSon(tempMax);
                    max.setLeftSon(leaf.getMiddleSon());
                    max.setRightSon(leaf.getRightSon());
                    tempMin.setParent(min);
                    tempMax.setParent(min);
                    leaf.getMiddleSon().setParent(max);
                    leaf.getRightSon().setParent(max);
                } else if (tempMin.getKeyL().compareTo(leaf.getKeyR()) > 0) {
                    min.setLeftSon(leaf.getLeftSon());
                    min.setRightSon(leaf.getMiddleSon());
                    max.setLeftSon(tempMin);
                    max.setRightSon(tempMax);
                    tempMin.setParent(max);
                    tempMax.setParent(max);
                    leaf.getLeftSon().setParent(min);
                    leaf.getMiddleSon().setParent(min);
                } else {
                    min.setLeftSon(leaf.getLeftSon());
                    min.setRightSon(tempMin);
                    max.setLeftSon(tempMax);
                    max.setRightSon(leaf.getRightSon());
                    tempMin.setParent(min);
                    tempMax.setParent(max);
                    leaf.getLeftSon().setParent(min);
                    leaf.getRightSon().setParent(max);
                }
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
                    //leafParent.setLeftSon(min);
                    //leafParent.setRightSon(max);
                    leaf = leafParent;
                    node = middle;
                }
            } else {
                //TTTreeNode<K, T> newRoot = middle;
                middle.setLeftSon(min);
                middle.setRightSon(max);
                min.setParent(middle);
                max.setParent(middle);
                this.root = middle;
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
        return (key.compareTo(result.getKeyL()) == 0 || result.getKeyR().compareTo(key) == 0) && parent != result ? parent : null;
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
        //System.out.println(result.getKeyL() + " -----------");
        //System.out.println(key + " -----------");
        //System.out.println(key == result.getKeyL());
        return (key.compareTo(result.getKeyL()) == 0 || result.getKeyR().compareTo(key) == 0) ? result : null;
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

    public TTTreeNode<K, T> inOrder(TTTreeNode<K, T> node) {
        ArrayList<TTTreeNode<K, T>> a = new ArrayList<>();
        if (root == null)
            return null;
        if (node.isLeaf()) {
            return node;
        }
        if (node.hasLeftSon()) {
            inOrder(node.getLeftSon());
        }
        if (node.hasRightSon()) {
            inOrder(node.getRightSon());
        }
        if (node.hasMiddleSon()) {
            inOrder(node.getMiddleSon());
        }
        return node;
    }

    public T remove(K key) {
        TTTreeNode<K, T> node = search(key);
        if (node == null) {
            System.out.println("Prvok neexistuje");
            return null;
        }
        TTTreeNode<K, T> deletedNode = new TTTreeNode<>(node.getKeyL(), node.getDataL());
        deletedNode.setKeyR(deletedNode.getKeyR());
        deletedNode.setDataR(deletedNode.getDataR());
        if (!tryToRemove(node, key)) {
            System.out.println("Mazanie sa nepodarilo");
            return null;
        }
        size--;
        if (deletedNode.isThreeNode()) {
            if (deletedNode.getKeyL().compareTo(key) == 0) {
                return deletedNode.getDataL();
            } else {
                return deletedNode.getDataR();
            }
        }
        return deletedNode.getDataL();
    }

    private boolean tryToRemove(TTTreeNode<K, T> node, K key) {
        if (node.isLeaf()) {
            if (!node.isThreeNode()) {
                if (node == this.root) {
                    this.root = null;
                    height--;
                    return true;
                } else {
//                    if (node.getParent().getMiddleSon() == node) {
//                        node.getParent().setMiddleSon(null);
//                    } else if (node.getParent().getLeftSon() == node) {
//                        node.getParent().setLeftSon(null);
//                    } else {
//                        node.getParent().setRightSon(null);
//                    }
                    node.setKeyL(null);
                    node.setDataL(null);
                    //return node.getDataL();
                    //node.setParent(null);
                }
            } else {
                if (node.getKeyL().compareTo(key) == 0) {
                    node.setKeyL(node.getKeyR());
                    node.setDataL(node.getDataR());
                } else {
                    node.setKeyR(null);
                    node.setDataR(null);
                }
                return true;
            }
        }
        TTTreeNode<K, T> inOrderLeaf = node;
        if (!node.isLeaf()) {
            inOrderLeaf = findInOrderLeaf(node, key);
            //System.out.println(inOrderLeaf.getKeyL());
        }

        if (!node.isLeaf()) {
            if (node.getKeyL().compareTo(key) == 0) {
                if (node.isThreeNode()) {
                    if (node.getKeyR().compareTo(inOrderLeaf.getKeyL()) > 0) {
                        node.setKeyL(inOrderLeaf.getKeyL());
                        node.setDataL(inOrderLeaf.getDataL());
                    } else {
                        K tempLeftKey = node.getKeyL();
                        T tempLeftData = node.getDataL();
                        node.setKeyL(inOrderLeaf.getKeyL());
                        node.setDataL(inOrderLeaf.getDataL());
                        node.setKeyR(tempLeftKey);
                        node.setDataR(tempLeftData);
                    }
                } else {
                    node.setKeyL(inOrderLeaf.getKeyL());
                    node.setDataL(inOrderLeaf.getDataL());
                }
            } else {
                if (node.getKeyL().compareTo(inOrderLeaf.getKeyL()) < 0) {
                    node.setKeyR(inOrderLeaf.getKeyL());
                    node.setDataR(inOrderLeaf.getDataL());
                } else {
                    K tempLeftKey = node.getKeyR();
                    T tempLeftData = node.getDataR();
                    node.setKeyR(inOrderLeaf.getKeyL());
                    node.setDataR(inOrderLeaf.getDataL());
                    node.setKeyL(tempLeftKey);
                    node.setDataL(tempLeftData);
                }
            }
            if (inOrderLeaf.isThreeNode()) {
                inOrderLeaf.setKeyL(inOrderLeaf.getKeyR());
                inOrderLeaf.setDataL(inOrderLeaf.getDataR());
            } else {
                inOrderLeaf.setKeyL(null);
                inOrderLeaf.setDataL(null);
            }
        }

        while (true) {
            //System.out.println("while");
            if (inOrderLeaf.getKeyL() != null) {
                return true;
            }

            if (inOrderLeaf == root) {
                if (inOrderLeaf.hasLeftSon()) {
                    root = inOrderLeaf.getLeftSon();
                }
                if (inOrderLeaf.hasMiddleSon()) {
                    root = inOrderLeaf.getMiddleSon();
                }
                if (inOrderLeaf.hasRightSon()) {
                    root = inOrderLeaf.getRightSon();
                }
                if (!inOrderLeaf.hasLeftSon() && !inOrderLeaf.hasMiddleSon() && !inOrderLeaf.hasRightSon()) {
                    root = null;
                }
                if (inOrderLeaf.hasLeftSon() && inOrderLeaf.hasRightSon()) {
                    System.out.println("Possible error: inOrderLeaf.hasLeftSon() && inOrderLeaf.hasRightSon()");
                }
                if (inOrderLeaf.hasLeftSon() && inOrderLeaf.hasMiddleSon()) {
                    System.out.println("Possible error: inOrderLeaf.hasLeftSon() && inOrderLeaf.hasMiddleSon()");
                }
                if (inOrderLeaf.hasRightSon() && inOrderLeaf.hasMiddleSon()) {
                    System.out.println("Possible error: inOrderLeaf.hasRightSon() && inOrderLeaf.hasMiddleSon()");
                }
                height--;
                return true;
            }

            SonType inOrderLeafSonType = getSonType(inOrderLeaf);
            TTTreeNode<K, T> parent = inOrderLeaf.getParent();
            if (inOrderLeafSonType == SonType.LEFT) {
                System.out.println("inOrderLeafSonType == SonType.LEFT");
                if (parent.getRightSon().isThreeNode()) {
                    inOrderLeaf.setKeyL(parent.getKeyL());
                    inOrderLeaf.setDataL(parent.getDataL());
                    parent.setKeyL(parent.getMiddleSon().getKeyL());
                    parent.setDataL(parent.getMiddleSon().getDataL());
                    parent.getMiddleSon().setKeyL(parent.getMiddleSon().getKeyR());
                    parent.getMiddleSon().setDataL(parent.getMiddleSon().getDataR());
                    parent.getMiddleSon().setKeyR(null);
                    parent.getMiddleSon().setDataR(null);
                    if (!inOrderLeaf.isLeaf() && !parent.getMiddleSon().isLeaf()) {
                        if (!inOrderLeaf.getLeftSon().hasKeyL()) {
                            inOrderLeaf.setLeftSon(inOrderLeaf.getRightSon());
                        }
                        inOrderLeaf.setRightSon(parent.getMiddleSon().getLeftSon());
                        parent.getMiddleSon().setLeftSon(parent.getMiddleSon().getMiddleSon());
                        parent.getMiddleSon().setMiddleSon(null);
                    }
                    return true;
                } else {
                    if (parent.isThreeNode()) {
                        K tempLeftKey = parent.getMiddleSon().getKeyL();
                        T tempLeftData = parent.getMiddleSon().getDataL();
                        parent.getMiddleSon().setKeyL(parent.getKeyL());
                        parent.getMiddleSon().setDataL(parent.getDataL());
                        parent.getMiddleSon().setKeyR(tempLeftKey);
                        parent.getMiddleSon().setDataR(tempLeftData);

                        parent.getMiddleSon().setMiddleSon(parent.getMiddleSon().getLeftSon());

                        if (!parent.getLeftSon().isLeaf()) {
                            if (parent.getLeftSon().getRightSon().hasKeyL()) {
                                parent.getMiddleSon().setLeftSon(parent.getLeftSon().getRightSon());
                            } else {
                                parent.getMiddleSon().setLeftSon(parent.getLeftSon().getLeftSon());
                            }
                        }

                        parent.getMiddleSon().setLeftSon(parent.getLeftSon().getLeftSon());
                        parent.setLeftSon(parent.getMiddleSon());
                        parent.setMiddleSon(null);

                        parent.setKeyL(parent.getKeyR());
                        parent.setDataL(parent.getDataR());
                        parent.setKeyR(null);
                        parent.setDataR(null);
                    } else {
                        K tempLeftKey = parent.getRightSon().getKeyL();
                        T tempLeftData = parent.getRightSon().getDataL();
                        parent.getRightSon().setKeyL(parent.getKeyL());
                        parent.getRightSon().setDataL(parent.getDataL());
                        parent.getRightSon().setKeyR(tempLeftKey);
                        parent.getRightSon().setDataR(tempLeftData);
                        parent.setKeyL(null);
                        parent.setDataL(null);
                    }
                    //parent.setLeftSon(null);
                }
            } else if (inOrderLeafSonType == SonType.RIGHT) {
                if (parent.getLeftSon().isThreeNode()) {
                    inOrderLeaf.setKeyL(parent.getKeyR());
                    inOrderLeaf.setDataL(parent.getDataR());
                    parent.setKeyR(parent.getMiddleSon().getKeyR());
                    parent.setDataR(parent.getMiddleSon().getDataR());
                    parent.getMiddleSon().setKeyR(null);
                    parent.getMiddleSon().setDataR(null);
                    if (!inOrderLeaf.isLeaf() && !parent.getMiddleSon().isLeaf()) {
                        if (!inOrderLeaf.getRightSon().hasKeyL()) {
                            inOrderLeaf.setRightSon(inOrderLeaf.getLeftSon());
                        }
                        inOrderLeaf.setLeftSon(parent.getMiddleSon().getRightSon());
                        parent.getMiddleSon().setRightSon(parent.getMiddleSon().getMiddleSon());
                        parent.getMiddleSon().setMiddleSon(null);
                    }
                    return true;
                } else {
                    if (parent.isThreeNode()) {
                        //System.out.println("here");
                        parent.getMiddleSon().setKeyR(parent.getKeyR());
                        parent.getMiddleSon().setDataR(parent.getDataR());

                        parent.getMiddleSon().setMiddleSon(parent.getMiddleSon().getRightSon());

                        if (!parent.getRightSon().isLeaf()) {
                            if (parent.getRightSon().getRightSon().hasKeyL()) {
                                parent.getMiddleSon().setRightSon(parent.getRightSon().getRightSon());
                            } else {
                                parent.getMiddleSon().setRightSon(parent.getRightSon().getLeftSon());
                            }
                        }

                        parent.setRightSon(parent.getMiddleSon());

//                        System.out.println(parent.getMiddleSon().getKeyL());
//                        System.out.println(parent.getKeyL());
//                        System.out.println(parent.getRightSon().getKeyL());

                        parent.setMiddleSon(null);

                        parent.setKeyR(null);
                        parent.setDataR(null);
                    } else {
                        parent.getLeftSon().setKeyR(parent.getKeyL());
                        parent.getLeftSon().setDataR(parent.getDataL());
                        parent.setKeyL(null);
                        parent.setDataL(null);
                    }
                    //parent.setRightSon(null);
                }
            } else {
                if (parent.getLeftSon().isThreeNode() || parent.getRightSon().isThreeNode()) {
                    if (parent.getLeftSon().isThreeNode()) {
                        inOrderLeaf.setKeyL(parent.getKeyL());
                        inOrderLeaf.setDataL(parent.getDataL());
                        parent.setKeyL(parent.getLeftSon().getKeyR());
                        parent.setDataL(parent.getLeftSon().getDataR());
                        parent.getMiddleSon().setKeyR(null);
                        parent.getMiddleSon().setDataR(null);
                        if (!inOrderLeaf.isLeaf() && !parent.getLeftSon().isLeaf()) {
                            if (!inOrderLeaf.getRightSon().hasKeyL()) {
                                inOrderLeaf.setRightSon(inOrderLeaf.getLeftSon());
                            }
                            inOrderLeaf.setLeftSon(parent.getLeftSon().getRightSon());
                            parent.getLeftSon().setRightSon(parent.getLeftSon().getMiddleSon());
                            parent.getLeftSon().setMiddleSon(null);
                        }
                        return true;
                    } else if (parent.getRightSon().isThreeNode()) {
                        inOrderLeaf.setKeyL(parent.getKeyR());
                        inOrderLeaf.setDataL(parent.getDataR());
                        parent.setKeyR(parent.getRightSon().getKeyL());
                        parent.setDataR(parent.getRightSon().getDataL());
                        parent.getRightSon().setKeyL(parent.getRightSon().getKeyR());
                        parent.getRightSon().setDataL(parent.getRightSon().getDataR());
                        parent.getRightSon().setKeyR(null);
                        parent.getRightSon().setDataR(null);
                        if (!inOrderLeaf.isLeaf() && !parent.getRightSon().isLeaf()) {
                            if (!inOrderLeaf.getLeftSon().hasKeyL()) {
                                inOrderLeaf.setLeftSon(inOrderLeaf.getRightSon());
                            }
                            inOrderLeaf.setRightSon(parent.getRightSon().getLeftSon());
                            parent.getRightSon().setLeftSon(parent.getRightSon().getMiddleSon());
                            parent.getRightSon().setMiddleSon(null);
                        }
                        return true;
                    }
                } else {
                    if (!parent.isThreeNode()) {
                        System.out.println("Error: !parent.isThreeNode()");
                    }
                    parent.getLeftSon().setKeyR(parent.getKeyL());
                    parent.getLeftSon().setDataR(parent.getDataL());
                    parent.setKeyL(parent.getKeyR());
                    parent.setDataL(parent.getDataR());

                    parent.getLeftSon().setMiddleSon(parent.getLeftSon().getRightSon());

                    if (!parent.getMiddleSon().isLeaf()) {
                        if (parent.getMiddleSon().getRightSon().hasKeyL()) {
                            parent.getLeftSon().setRightSon(parent.getMiddleSon().getRightSon());
                        } else {
                            parent.getLeftSon().setRightSon(parent.getMiddleSon().getLeftSon());
                        }
                    }

                    parent.setMiddleSon(null);

                    parent.setKeyR(null);
                    parent.setDataR(null);
                }
            }

            inOrderLeaf = inOrderLeaf.getParent();
        }
        //return false;
    }

    private SonType getSonType(TTTreeNode<K, T> node) {
        if (node != null) {
            TTTreeNode<K, T> parent = node.getParent();
            if (parent != null) {
                if (parent.getRightSon() == node) {
                    return SonType.RIGHT;
                }
                if (parent.getMiddleSon() == node) {
                    return SonType.MIDDLE;
                }
                if (parent.getLeftSon() == node) {
                    return SonType.LEFT;
                }
            }
        }
        return null;
    }

    private TTTreeNode<K, T> findInOrderLeaf(TTTreeNode<K, T> node, K key) {
        TTTreeNode<K, T> result = null;
        if (node.getKeyL().compareTo(key) == 0) {
            if (node.isThreeNode()) {
                if (node.hasMiddleSon()) {
                    result = node.getMiddleSon();
                } else if (node.hasRightSon()) {
                    result = node.getRightSon();
                    System.out.println("Error in findInOrderLeaf, this should not happened.");
                }
            } else {
                if (node.hasRightSon()) {
                    result = node.getRightSon();
                }
            }
        } else {
            if (node.hasRightSon()) {
                result = node.getRightSon();
            }
        }
        if (result != null) {
            while (true) {
                if (result.hasLeftSon()) {
                    result = result.getLeftSon();
                } else {
                    break;
                }
            }
            return result;
        }
        System.out.println("Error in findInOrderLeaf, this should not happened.");
        return null;
    }


    private boolean extractNode(TTTreeNode<K, T> node) {
//        TTTreeNode<K, T> parent = node.getParent();
//        if (parent == null) {
//            this.root = null;
//            --size;
//            return true;
//        }
//        if (parent.getKey().compareTo(node.getKey()) < 0) {
//            parent.setRightSon(node);
//        } else {
//            parent.setLeftSon(node);
//            --size;
//        }
        return true;
    }

    public int getHeight() {
        return height;
    }
}
