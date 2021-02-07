package Q1;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class RedBlackTreeTest {

    @Test
    public void createRBTreeInteger() {
        RedBlackTree<Integer> rbTreeInteger = RedBlackTree.createRBTreeInteger(6);
        assertEquals(rbTreeInteger.highOfTree(rbTreeInteger.getRoot()),6);

    }

    @Test
    public void createRBTreeString() {
         RedBlackTree<String> stringRedBlackTree = RedBlackTree.createRBTreeString(6);
         System.out.println(stringRedBlackTree.toString());
         assertEquals(stringRedBlackTree.highOfTree(stringRedBlackTree.getRoot()),6);
    }
}