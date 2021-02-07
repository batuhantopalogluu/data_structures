package Q3;

import KW.Edge;
import KW.ListGraph;

import static Methodlar.methods.*;

/**
 * 14 edge(iki yonlu bakarasak 28) ile 10 vertex'e sahip undirected , cycle ve agirligi olmayan bir graph olusturdum.
 */
public class part3 {
    public static void main(String[] args) {

        ListGraph part3 = new ListGraph(10, false);

        Edge edge0 = new Edge(0, 1);
        Edge edge1 = new Edge(0, 3);
        Edge edge2 = new Edge(1, 2);
        Edge edge3 = new Edge(1, 4);
        Edge edge4 = new Edge(2, 5);
        Edge edge5 = new Edge(3, 6);
        Edge edge6 = new Edge(3, 4);
        Edge edge7 = new Edge(4, 5);
        Edge edge8 = new Edge(4, 7);
        Edge edge9 = new Edge(5, 8);
        Edge edge10 = new Edge(6, 7);
        Edge edge11 = new Edge(6, 9);
        Edge edge12 = new Edge(7, 8);
        Edge edge13 = new Edge(7, 9);


        part3.insert(edge0);
        part3.insert(edge1);
        part3.insert(edge2);
        part3.insert(edge3);
        part3.insert(edge4);
        part3.insert(edge5);
        part3.insert(edge6);
        part3.insert(edge7);
        part3.insert(edge8);
        part3.insert(edge9);
        part3.insert(edge10);
        part3.insert(edge11);
        part3.insert(edge12);
        part3.insert(edge13);

        plot_graph(part3);
        System.out.println("\nreturn value of 'is_undirected(part3)' : " + is_undirected(part3));
        System.out.println("return value of 'is_acylcle_graph(part3)'"+ is_acylcle_graph(part3)+"\n");
    }
}
