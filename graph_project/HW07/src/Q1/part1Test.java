package Q1;

import KW.Edge;
import KW.ListGraph;
import org.junit.Test;

import java.util.Vector;

import static Methodlar.methods.*;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static org.junit.Assert.*;


/**
 * main code'un ciktilari ekrana basan degilde beklenen return degerleri ile kontrol eden test kodu
 */

public class part1Test {

    @Test
    public void main() {

        ListGraph listGraph = new ListGraph(10, true);

        Edge edge0 = new Edge(0, 1, 10);
        Edge edge1 = new Edge(0, 4, 5);
        Edge edge2 = new Edge(0, 3, 7);
        Edge edge3 = new Edge(6, 2, 4);
        Edge edge4 = new Edge(6, 3, 10);
        Edge edge5 = new Edge(6, 7, 5);
        Edge edge6 = new Edge(7, 8, 7);
        Edge edge7 = new Edge(7, 4, 20);
        Edge edge8 = new Edge(3, 4, 11);
        Edge edge9 = new Edge(3, 8, 6);
        Edge edge10 = new Edge(3, 7, 8);
        Edge edge11 = new Edge(3, 2, 12);
        Edge edge12 = new Edge(3, 1, 13);
        Edge edge13 = new Edge(5, 8, 9);
        Edge edge14 = new Edge(9, 5, 8);
        Edge edge15 = new Edge(9, 4, 24);
        Edge edge16 = new Edge(9, 8, 10);
        Edge edge17 = new Edge(4, 8, 15);
        Edge edge18 = new Edge(4, 5, 6);
        Edge edge19 = new Edge(4, 1, 20);

        listGraph.insert(edge0);
        listGraph.insert(edge1);
        listGraph.insert(edge2);
        listGraph.insert(edge3);
        listGraph.insert(edge4);
        listGraph.insert(edge5);
        listGraph.insert(edge6);
        listGraph.insert(edge7);
        listGraph.insert(edge8);
        listGraph.insert(edge9);
        listGraph.insert(edge10);
        listGraph.insert(edge11);
        listGraph.insert(edge12);
        listGraph.insert(edge13);
        listGraph.insert(edge14);
        listGraph.insert(edge15);
        listGraph.insert(edge16);
        listGraph.insert(edge17);
        listGraph.insert(edge18);
        listGraph.insert(edge19);

        /* 1 */
        plot_graph(listGraph);
        // cikti testi göz ile yapilabilir sanirim.

        /* 2 */
        assertEquals(is_undirected(listGraph),false);

        /* 3 */
        assertEquals(is_acylcle_graph(listGraph),true);

        /* 4 */

        //distance parametre olarak cop deger gonderilip iceride degistirilir
        double[] distance = new double[1];

        //////////////////////////////////////////
        Vector<Integer> check1 = new Vector<>();
        check1.add(0);check1.add(3);check1.add(2);

        assertEquals(shortest_path(listGraph,0,2,distance),check1);

        if(distance[0]== 19.0){
            assertTrue(true);
        }
        else assertTrue(false);

        ///////////////////////////////////////////
        Vector<Integer> check2 = new Vector<>();
        check2.add(6);check2.add(3);check2.add(4);

        assertEquals(shortest_path(listGraph,6,4,distance),check2);

        if(distance[0]== 21.0){
            assertTrue(true);
        }
        else assertTrue(false);

        ////////////////////////////////////////////

        assertEquals(shortest_path(listGraph,0,9,distance),null);

        if(distance[0]== Infinity){
            assertTrue(true);
        }
        else assertTrue(false);
    }
}