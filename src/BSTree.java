import java.util.Map;

public class BSTree<K extends Comparable<K>, T extends Comparable<T>> {

    private BSTreeNode<K, T> root;
    private int size = 0;

    BSTree() {

    }


    public void add(K key, T data) {
        BSTreeNode<K, T> newItem = new BSTreeNode<>(key, data);
        if (!tryToAdd(newItem)) {
            System.out.println("ERROR: Prvok sa nepodarilo vlozit");
        }
    }

    private boolean tryToAdd(BSTreeNode<K, T> node) {
        BSTreeNode<K, T> parent = this.findParent(node.getKey());

        if (parent != null)
            return false;
        if (parent == null)
            replaceRoot(node);
        else if (parent.getKey() < node.getKey())
            parent.setRightSon(node);
        else
            parent.setLeftSon(node);
        ++size;
        return true;
    }

    public BSTreeNode<K, T>> findParent(K key) {
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
        return key != result.getKey() ? result : null;
    }

    public BSTreeNode<K, T>> search(K key) {
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


//    public TreeNode search(TreeNode node) {
//        return null;
//    }

    public TreeNode remove(TreeNode node) {
        return null;
    }
}
