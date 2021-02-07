package Q2;

import KW.Edge;
import KW.ListGraph;

import static Methodlar.methods.*;

/**
 * part 2 de tanimlanmis tarzda bir graph olusturup üzerinde cagrilmasi istenilen methodlari cagiriyorum.
 */
public class part2 {
    public static void main(String[] args) {

        ListGraph part2 = new ListGraph(15, false);

        Edge edge0 = new Edge(0, 1);
        Edge edge1 = new Edge(1, 2);
        Edge edge2 = new Edge(2, 3);
        Edge edge3 = new Edge(3, 4);
        Edge edge4 = new Edge(4, 5);
        Edge edge5 = new Edge(5, 6);
        Edge edge6 = new Edge(6, 7);
        Edge edge7 = new Edge(7, 8);
        Edge edge8 = new Edge(8, 9);
        Edge edge9 = new Edge(9, 10);
        Edge edge10 = new Edge(10, 11);
        Edge edge11 = new Edge(11, 12);
        Edge edge12 = new Edge(12, 13);
        Edge edge13 = new Edge(13, 14);

        part2.insert(edge0);
        part2.insert(edge1);
        part2.insert(edge2);
        part2.insert(edge3);
        part2.insert(edge4);
        part2.insert(edge5);
        part2.insert(edge6);
        part2.insert(edge7);
        part2.insert(edge8);
        part2.insert(edge9);
        part2.insert(edge10);
        part2.insert(edge11);
        part2.insert(edge12);
        part2.insert(edge13);

        plot_graph(part2);

        System.out.println("\nreturn value of 'is_undirected(part2)' : " + is_undirected(part2)+"\n");

        System.out.println("return value of 'is_acylcle_graph(part2)'"+ is_acylcle_graph(part2)+"\n");

        // connected ve undirected graph olduğu icin bütün vertexler
        // arasi connection var o yuzden return degerleri true olmali
        try {
            System.out.println("return value of 'is_connected(part2,0,5)' : " + is_connected(part2, 0, 5));
            System.out.println("return value of 'is_connected(part2,7,1)' : " + is_connected(part2, 7, 1));
            System.out.println("return value of 'is_connected(part2,3,12)' : " + is_connected(part2, 3, 12));
        }
        catch (Exception ex){
            System.out.println("exception handled");
        }
    }
}