package Q3;

import KW.BinarySearchTree;
import KW.BinarySearchTreeWithRotate;
import KW.BinaryTree;
import Q1.RedBlackTree;

import java.util.ArrayList;


/**
 * Self-balancing binary search tree using the algorithm defined
 * by Adelson-Velskii and Landis.
 * @author Koffman and Wolfgang
 */
public class AVLTree<E extends Comparable<E>>
        extends BinarySearchTreeWithRotate<E> {

    /*<listing chapter="9" number="2">*/
    /** Class to represent an AVL Node. It extends the
     * BinaryTree.Node by adding the balance field.
     */
    private static class AVLNode<E> extends Node<E> {

        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;
        /** Constant to indicate balanced */
        public static final int BALANCED = 0;
        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;
        /** balance is right subtree height - left subtree height */
        private int balance;

        // Methods
        /**
         * Construct a node with the given item as the data field.
         * @param item The data field
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        @Override
        public Node<E> getRight() {
            return super.getRight();
        }

        @Override
        public Node<E> getLeft() {
            return super.getLeft();
        }

        /**
         * Return a string representation of this object.
         * The balance value is appended to the contents.
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }

    /*</listing>*/
    // Data Fields
    /** Flag to indicate that height of tree has increased. */
    private boolean increase;


    // Methods
    /**
     * add starter method.
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     *         if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /*public AVLTree(){
        this();
    }*/

    /**
     * Recursive add method. Inserts the given object into the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root of the subtree with the item
     *         inserted
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;

            return localRoot;
        }
        else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        } else {
            // Insert solution to programming exercise 2, section 2, chapter 9 here
            // item > data
          //  System.out.println(localRoot.toString() + "*" + item.toString());
            localRoot.right = add((AVLNode<E>) localRoot.right, item);

            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) { ///////////////////
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        }
    }

    /**
     * Method to rebalance left.
     * @param localRoot Root of the AVL subtree
     *        that needs rebalancing
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // Obtain reference to left child.
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        // See whether left-right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child.
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            // Adjust the balances to be their new values after
            // the rotations are performed.
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        } else { //Left-Left case
            // In this case the leftChild (the new root)
            // and the root (new right child) will both be balanced
            // after the rotation.
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }

    /**
     * Method to rebalance right.
     * @param localRoot Root of the AVL subtree
     *        that needs rebalancing
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // Obtain reference to right child.
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        // See whether left-right heavy.
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain reference to left-right child.
            AVLNode<E> RightLeftChild = (AVLNode<E>) rightChild.left;
            // Adjust the balances to be their new values after
            // the rotations are performed.
            if (RightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                RightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else if (RightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                RightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            } else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform right rotation.
            localRoot.right = rotateRight(rightChild);
        } else { //right -right case
            // In this case the rightChild (the new root)
            // and the root (new left child) will both be balanced
            // after the rotation.
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root left.
        return  (AVLNode<E>)rotateLeft(localRoot);
    }

    /**
     * Method to decrement the balance field and to reset the value of
     * increase.
     * @param node The AVL node whose balance is to be incremented
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            // If now balanced, overall height has not increased.
            increase = false;
        }
    }

    /**
     * @param node The AVL node whose balance is to be decrement
     */
     private void incrementBalance(AVLNode<E> node) {
        // Increment the balance.
        node.balance++;
        if (node.balance == AVLNode.BALANCED) {
            increase = false;
        }
    }


    public AVLTree(){

    }

    public AVLTree(BinaryTree<E> binaryTree){
        if(!(isAVL(binaryTree))) throw new IllegalStateException("bu avl tree olamaz");
        else System.out.println("Bu bir avl tree ");
    }

    private boolean isAVL(BinaryTree<E> tree){

        ArrayList<Node<E>> leafs = new ArrayList<>();
        ArrayList<Integer> leafsDepth = new ArrayList<>();

        Postorder(tree.getRoot(),leafs,leafsDepth);

        int[] arr = new int[leafs.size()];

        for(int i = 0 ;i <leafs.size();i++)
            arr[i] = leafsDepth.get(i);

        int max = getMaxValue(arr);
        int min = getMinValue(arr);

        if((max - min) > 1) return false;

        return true;
    }

    /**
     * en yüksek derinliğe sahip leaf'i veren method
     * @param array bir tarafı veya iki tarafı null olan nodelar
     * @return maximum değer
     */
    private static int getMaxValue(int[] array) {
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    /**
     * en düşük yükseklikteki veriyi return eder
     * @param array array bir tarafı veya iki tarafı null olan nodelar
     * @return minumum değerlikli olan
     */
    private static int getMinValue(int[] array) {
        int minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    /**
     * verilen root'un leaf den node a yükseliğini verir.
     * @param root yüksekliği alıncak node
     * @return node un yüksekliği
     */
    private int highOfTree(Node<E> root){
        if(root == null){
            return 0;
        }
        int leftDeth = highOfTree(root.getLeft());
        int leftRight = highOfTree(root.getRight());

        if (leftDeth > leftRight)
            return (leftDeth + 1);
        else
            return (leftRight + 1);
    }

    /**
     * tree yi gezerek tek  veya iki çocuğu null olan node'ları bulup parametre olarak aldğı konteynerlara ekler
     * @param node root
     * @param leafs leaf veya yarı leafler
     * @param leafsDepth leaf veya yarı leaflerin yüksekliği
     */
    private void Postorder(Node node, ArrayList<Node<E>> leafs , ArrayList<Integer> leafsDepth){
        if (node == null)
            return;

        if(node.left == null || node.right == null){
            leafs.add(node);
            leafsDepth.add(highOfTree(node));
        }

        Postorder(node.left,leafs,leafsDepth);

        Postorder(node.right,leafs,leafsDepth);

    }

    public static void main(String[] args) {

        AVLTree<String> avlTree = new AVLTree<>();
        avlTree.add("The");
        avlTree.add("quick");
        avlTree.add("brown");
        avlTree.add("fox");
        avlTree.add("jump");
        avlTree.add("over");
        avlTree.add("the");
        avlTree.add("lazy");
        avlTree.add("dog");

        BinaryTree<String> check1= avlTree;


        BinarySearchTree<String> bst = new BinarySearchTreeWithRotate<>();
        bst.add("The");
        bst.add("quick");
        bst.add("dog");
        bst.add("fox");
        bst.add("jump");


        BinaryTree<String> check2= bst;

        try {

            AVLTree<String> isAvle = new AVLTree<>(check1);

            AVLTree<String> isAvle2 = new AVLTree<>(check2);
        }
        catch (Exception E){
            System.out.println(E.getMessage());
        }
    }
}
