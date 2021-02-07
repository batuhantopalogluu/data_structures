package Q2;

import org.junit.Test;

import static org.junit.Assert.*;

public class BTreeTest {
    @Test
    public void binarySearchInBtree() {

        // integer veriler tutan order'i 4 olan btree
        BTree<Integer> bTree = new BTree<>(4);
        bTree.add(5);
        bTree.add(10);
        bTree.add(15);
        bTree.add(25);
        bTree.add(7);
        bTree.add(16);
        bTree.add(35);
        bTree.add(1);
        bTree.add(8);

        //arananların iclerinde bulunması gereken nodelar önceden hesaplanir
        String expected1 = "7, 8";
        String expected2 = "25, 35";
        String expected3 = "1";
        String expected4 = "5, 10, 16";
        String expected5 = "15";

        assertEquals(bTree.binarySearchInBtree(8).toString(),expected1);
        assertEquals(bTree.binarySearchInBtree(25).toString(),expected2);
        assertEquals(bTree.binarySearchInBtree(1).toString(),expected3);
        assertEquals(bTree.binarySearchInBtree(5).toString(),expected4);
        assertEquals(bTree.binarySearchInBtree(15).toString(),expected5);

        //string veriler tutan order'ı 5 olan btree

        BTree<String> BTree = new BTree<>(5);
        BTree.add("The");
        BTree.add("quick");
        BTree.add("brown");
        BTree.add("fox");
        BTree.add("jumps");
        BTree.add("over");
        BTree.add("the");
        BTree.add("lazy");
        BTree.add("dog");

        //beklenen degerler
        String expectedS1 = "The, brown, dog";
        String expectedS2 = "quick, the";
        String expectedS3 = "fox, over";
        String expectedS4 = "jumps, lazy";

        assertEquals(BTree.binarySearchInBtree( "The").toString(),expectedS1);
        assertEquals(BTree.binarySearchInBtree("the").toString(),expectedS2);
        assertEquals(BTree.binarySearchInBtree("fox").toString(),expectedS3);
        assertEquals(BTree.binarySearchInBtree("lazy").toString(),expectedS4);

        // olmayan elemanı arayalım, null return etmeli

        assertEquals(BTree.binarySearchInBtree("Batuhan"),null);
    }
}