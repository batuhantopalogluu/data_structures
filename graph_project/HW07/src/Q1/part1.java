package Q1;

import KW.*;

import static Methodlar.methods.*;

public  class part1 {
    public static void main(String[] args) {

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
        Edge edge20 = new Edge(1, 0, 20);

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
      //  listGraph.insert(edge20);

        /* 1 */
            plot_graph(listGraph);

        /* 2 */
            System.out.println("\nreturn value of 'is_undirected(listGraph)' : " + is_undirected(listGraph)+"\n");

        /* 3 */
            System.out.println("return value of 'is_acylcle_graph(listGraph)'"+ is_acylcle_graph(listGraph)+"\n");

        /* 4 */

            //distance parametre olarak cop deger gonderilip iceride degistirilir
            double[] distance = new double[1];

            System.out.println("0 ile 2 vertxler arası path : "+
                    shortest_path(listGraph,0,2,distance)+ " distance : "+distance[0] );

            System.out.println("6 ile 4 vertxler arası path : "+
                    shortest_path(listGraph,6,4,distance)+ " distance : "+distance[0] );

            System.out.println("0 ile 9 vertxler arası path : "+
                    shortest_path(listGraph,0,9,distance)+ " distance : "+distance[0] );

    }
}