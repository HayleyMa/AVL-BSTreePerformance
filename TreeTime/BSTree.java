
package TreeTime;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * A binary search tree <br>
 * Requires JDK 1.8 for Function
 * @param <E> the tree data type
 * @since 10-16-2017
 * @see BSTreeAPI, BSTreeException 
 */
public class BSTree<E extends Comparable<E>> implements BSTreeAPI<E> {

    /**
     * the root of this tree
     */
    private Node root;
    /**
     * the number of nodes in this tree
     */
    private int size;

    /**
     * A node of a tree stores a data item and references to the child nodes to
     * the left and to the right.
     */
    private class Node {

        /**
         * the data in this node
         */
        public E data;
        /**
         * A reference to the left subtree rooted at this node.
         */
        public Node left;
        /**
         * A reference to the right subtree rooted at this node
         */
        public Node right;
    }

    /**
     * Constructs an empty tree
     */
    public BSTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(E item) {
        Node newNode = new Node();
        newNode.data = item;
        if (size == 0) {
            root = newNode;
            size++;
        } else {
            Node tmp = root;
            while (true) {
                int d = tmp.data.compareTo(item);
                if (d == 0) {
                    /* Key already exists. (update) */
                    tmp.data = item;
                    return;
                } else if (d > 0) {
                    if (tmp.left == null) {
                        /* If the key is less than tmp */
                        tmp.left = newNode;
                        size++;
                        return;
                    } else {
                        /* continue searching for insertion pt. */
                        tmp = tmp.left;
                    }
                } else if (tmp.right == null) {/* If the key is greater than tmp */
                    tmp.right = newNode;
                    size++;
                    return;
                } else {
                    /* continue searching for insertion point*/
                    tmp = tmp.right;
                }
            }
        }
    }

    public boolean inTree(E item) {
        return search(item) != null;
    }

    public void remove(E item) {
        Node nodeptr = search(item);
        if (nodeptr != null) {
            remove(nodeptr);
            size--;
        }
    }

    public void traverse(Function func) {
        traverse(root, func);
    }

    public E retrieve(E key) throws BSTreeException {
        if (size == 0) {
            throw new BSTreeException("Non-empty tree expected on retrieve().");
        }
        Node nodeptr = search(key);
        if (nodeptr == null) {
            throw new BSTreeException("Existent key expected on retrieve().");
        }
        return nodeptr.data;
    }

    public int size() {
        return size;
    }

    /*===> BEGIN: Augmented public methods <===*/
    /**
     * Computes the depth of the specified search key in this tree.
     *
     * @param item the search key
     * @return the depth of the specified search key if it is in the. this tree.
     * If it is not, -1-d, where d is the depth at which it would have been
     * found it inserted in the current tree.
     */
    public int depth(E item) {//Implement this method
        Node keyNode = root;
        int depth = 0;
        while (keyNode != null && keyNode.data.compareTo(item) != 0) {
            if (keyNode.data.compareTo(item) > 0) {
                keyNode = keyNode.left;
                depth++;
            } else {
                keyNode = keyNode.right;
                depth++;
            }
        }
        if (keyNode == null) {
            return -1 - depth;
        }
        return depth;
    }

    /**
     * Give the heigh of this tree.
     *
     * @return the height of this tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Traverses this tree in level-order and applies the specified function to
     * the data in each node.
     *
     * @param func the function to apply to the data in each node
     */
    public void levelTraverse(Function func) {
        if (!isEmpty()) {
            ArrayList<Node> list = new ArrayList();
            list.add(root);
            while (!list.isEmpty()) {
                if (list.get(0).left != null) {
                    list.add(list.get(0).left);
                }
                if (list.get(0).right != null) {
                    list.add(list.get(0).right);
                }
                func.apply(list.remove(0).data);
            }
        }
    }

    /**
     * Gives the diameter of this tree
     *
     * @return the diameter of this tree
     */
    public int diameter() {
        return diameter(root);
    }

    /* END: Augmented public methods */

    /**
     * A recursive auxiliary method for the inorderTraver method that
     *
     * @param node a reference to a Node object
     * @param func a function that is applied to the data in each node as the
     * tree is traversed in order.
     */
    private void traverse(Node node, Function func) {
        if (node != null) {
            traverse(node.left, func);
            func.apply(node.data);
            traverse(node.right, func);
        }
    }

    /**
     * An auxiliary method that supports the search method
     *
     * @param key a data key
     * @return a reference to the Node object whose data has the specified key.
     */
    private Node search(E key) {
        Node current = root;
        while (current != null) {
            int d = current.data.compareTo(key);
            if (d == 0) {
                return current;
            } else if (d > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    /**
     * An auxiliary method that gives a Node reference to the parent node of the
     * specified node
     *
     * @param node a reference to a Node object
     * @return a reference to the parent node of the specified node
     */
    private Node findParent(Node node) {
        Node tmp = root;
        if (tmp == node) {
            return null;
        }
        while (true) {
            assert tmp.data.compareTo(node.data) != 0;
            if (tmp.data.compareTo(node.data) > 0) {
                /* this assert is not needed but just
               in case there is a bug         */
                assert tmp.left != null;
                if (tmp.left == node) {
                    return tmp;
                } else {
                    tmp = tmp.left;
                }
            } else {
                assert tmp.right != null;
                if (tmp.right == node) {
                    return tmp;
                } else {
                    tmp = tmp.right;
                }
            }
        }
    }

    /**
     * An auxiliary method that deletes the specified node from this tree
     *
     * @param node the node to be deleted
     */
    private void remove(Node node) {
        E theData;
        Node parent, replacement;
        parent = findParent(node);
        if (node.left != null) {
            if (node.right != null) {
                replacement = node.right;
                while (replacement.left != null) {
                    replacement = replacement.left;
                }
                theData = replacement.data;
                remove(replacement);
                node.data = theData;
                return;
            } else {
                replacement = node.left;
            }
        } else if (node.right != null) {
            replacement = node.right;
        } else {
            replacement = null;
        }
        if (parent == null) {
            root = replacement;
        } else if (parent.left == node) {
            parent.left = replacement;
        } else {
            parent.right = replacement;
        }
    }

    /* BEGIN: Augmented Private Auxiliary Methods */
    /**
     * An auxiliary method that recursively determines the height of a subtree
     * rooted at the specified node.
     *
     * @param node a root of a subtree.
     */
    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Recursively computes the diameter of the subtree rooted at the specified
     * node
     *
     * @param node the root of a subtree
     * @return the diameter of the subtree rooted at the specified node
     */
    private int diameter(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(3 + height(node.left) + height(node.right), Math.max(diameter(node.left), diameter(node.right)));
    }
    /* END: Augmented Private Auxiliary Methods */
}
