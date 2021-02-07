package Q1;
import KW.*;


/*<listing chapter="9" section="3">*/

/**
 * RedBlackTree.java
 * Implements a Red-Black binary search tree
 * Red-Black trees were invented by Bayer with refinments
 * (the color convention) introduced by Guibas and Sedgewick.
 * @author Koffman and Wolfgang
 */
public class RedBlackTree<E extends Comparable<E>>
        extends BinarySearchTreeWithRotate<E> {

    /*<listing chapter="9" number="4">*/

    /**
     * Nested class to represent a Red-Black node.
     */
    private static class RedBlackNode<E> extends Node<E> {
        // Additional data members

        /**
         * Color indicator. True if red, false if black.
         */
        private boolean isRed;

        // Constructor

        /**
         * Create a RedBlackNode with the default color of red
         * and the given data field.
         *
         * @param item The data field
         */
        public RedBlackNode(E item) {
            super(item);
            isRed = true;
        }

        public boolean isRed() {
            return isRed;
        }

        public RedBlackNode<E> getLeft() {
            return (RedBlackNode<E>) super.getLeft();
        }

        public RedBlackNode<E> getRight() {
            return (RedBlackNode<E>) super.getRight();
        }
        // Methods

        /**
         * Return a string representation of this object.
         * The color (red or black) is appended to the
         * node's contents.
         *
         * @return String representation of this object
         */
        @Override
        public String toString() {
            if (isRed) {
                return "Red  : " + super.toString();
            } else {
                return "Black: " + super.toString();
            }
        }
    }
    /*</listing>*/
// Insert solution to programming project 6, chapter -1 here

    /**
     * Insert an item into the tree. This is the starter method
     * of a recursive process.
     *
     * @param item - The item to be inserted
     * @return true if item inserted, false if item already in the tree.
     */
    @Override
    public boolean add(E item) {
        if (root == null) {
            root = new RedBlackNode<E>(item);
            ((RedBlackNode<E>) root).isRed = false; // root is black.
            return true;
        } else {
            root = add((RedBlackNode<E>) root, item);
            ((RedBlackNode<E>) root).isRed = false; // root is always black.
            return addReturn;
        }
    }

    /**
     * Recursive add method.
     *
     * @param localRoot - The root of the subtree
     * @param item      - The item to be inserted
     * @return updated local root of the subtree
     */
    private Node<E> add(RedBlackNode<E> localRoot, E item) {
        if (item.compareTo(localRoot.data) == 0) {
            // item already in the tree.
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item < localRoot.data.
            if (localRoot.left == null) {
                // Create new left child.
                localRoot.left = new RedBlackNode<E>(item);
                addReturn = true;
                return localRoot;
            } else { // Need to search.
                // Check for two red children, swap colors if found.
                moveBlackDown(localRoot);
                // Recursively add on the left.
                localRoot.left = add((RedBlackNode<E>) localRoot.left, item);

                // See whether the left child is now red
                if (((RedBlackNode<E>) localRoot.left).isRed) {

                    if (localRoot.left.left != null && ((RedBlackNode<E>) localRoot.left.left).isRed) {
                        // Left-left grandchild is also red.

                        // Single rotation is necessary.
                        ((RedBlackNode<E>) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    } else if (localRoot.left.right != null && ((RedBlackNode<E>) localRoot.left.right).isRed) {
                        // Left-right grandchild is also red.
                        // Double rotation is necessary.
                        localRoot.left = rotateLeft(localRoot.left);
                        ((RedBlackNode<E>) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    }
                }
                return localRoot;
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////
        } else { // item > localRoot.data
            // Insert solution to programming exercise 1, section 3, chapter 9 here
            if (localRoot.right == null) {
                // Create new left child.
                localRoot.right = new RedBlackNode<E>(item);
                addReturn = true;
                return localRoot;
            } else { // Need to search.
                // Check for two red children, swap colors if found.
                moveBlackDown(localRoot);
                // Recursively add on the left.
                localRoot.right = add((RedBlackNode<E>) localRoot.right, item);

                // See whether the left child is now red
                if (((RedBlackNode<E>) localRoot.right).isRed) {

                    if (localRoot.right.right != null && ((RedBlackNode<E>) localRoot.right.right).isRed) {
                        // Left-left grandchild is also red.

                        // Single rotation is necessary.
                        ((RedBlackNode<E>) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    } else if (localRoot.right.left != null && ((RedBlackNode<E>) localRoot.right.left).isRed) {
                        // Left-right grandchild is also red.
                        // Double rotation is necessary.
                        localRoot.right = rotateLeft(localRoot.right);
                        ((RedBlackNode<E>) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    }
                }
                return localRoot;
            }
        }
    }

    /**
     * @param localRoot işlem yapılacak yerelroot
     */
    private void moveBlackDown(RedBlackNode<E> localRoot) {

        if (localRoot.getLeft() != null && localRoot.getRight() != null && localRoot.getLeft().isRed && localRoot.getRight().isRed) {
            localRoot.getLeft().isRed = false;
            localRoot.getRight().isRed = false;
            localRoot.isRed = true;
        }
    }

    /**
     * rb tree nin verilen node'un leaf'den uzaklığını hesaplar
     * root node için bu yüksekliğe eşittir.
     * @param root yükseklikte baz alınacak node
     * @return node'un altan yukarı yüksekliği
     */
    protected int highOfTree(Node<E> root) {
        if (root == null) {
            return -1;
        }
        int leftDeth = highOfTree(root.getLeft());
        int leftRight = highOfTree(root.getRight());

        if (leftDeth > leftRight) return (leftDeth + 1);
        else return (leftRight + 1);
    }

    /**
     * ınteger veriler tutan tree oluşturur
     * @param height oluşturulacak ağaçın yüksekliği
     * @return oluşturulan ağaç
     */
    public static RedBlackTree<Integer> createRBTreeInteger(int height){
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        for (int i = 1000;rbt.highOfTree(rbt.root)<height;i--){
            rbt.add(i);
        }
        return rbt;
    }

    /**
     * string veriler tutan tree oluşturur
     * @param height oluşturulacak ağaçın yüksekliği
     * @return oluşturulan ağaç
     */
    public static RedBlackTree<String> createRBTreeString(int height){
        RedBlackTree<String> rbt = new RedBlackTree<>();

        char a = 'a';
        for (int i = (int)a; rbt.highOfTree(rbt.root)<height;i--){
            rbt.add(Character.toString(((char)i)));
        }
        return rbt;
    }

    public static void main(String[] args) {

        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        rbt.add(5);
        rbt.add(6);
        rbt.add(4);
        rbt.add(3);
        rbt.add(2);
        rbt.add(1);

        System.out.println(rbt.highOfTree(rbt.root));
        System.out.println(rbt.toString());

    }
}

