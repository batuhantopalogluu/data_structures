package Methodlar;

import KW.Edge;
import KW.Graph;

import java.util.*;

import static KW.BreadthFirstSearch.breadthFirstSearch;
import static KW.DijkstrasAlgorithm.dijkstrasAlgorithm;
import static java.lang.System.exit;
import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * partlarin iclerinde kullandigimiz methodlari burada tanimladim.
 */
public class methods {

    /**
     * graph directed olsa bile vertexler arası zit yönlü ayni
     * agirlikli edge'ler bulursak oda undirected sayilir.
     * Herhangi bir yer debu durumu tezatlik bulursak false return ediyoruz
     * @param graph kontrol edilecel graph
     * @return undirected olup olmama durumu
     */
    public static boolean is_undirected(Graph graph){
        Iterator<Edge> iter;

        for (int i = 0; i < graph.getNumV(); i++) {
            iter = graph.edgeIterator(i);

            while (iter.hasNext()) {
                Edge temp = iter.next();

                if(graph.isEdge(temp.getDest(),temp.getSource())){

                    if(graph.getEdge(temp.getDest(),temp.getSource()).getWeight() != temp.getWeight()) {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * graph uzerinde v1 ve v2 arasinda bag olup olmadigini kontrol eden method
     * @param graph v1 ve v2 nin üzerinde oldugu methodun uzerinde calisicagi graph
     * @param v1 connection in aranacagi ilk node
     * @param v2 connection in aranacagi ikinci node
     * @return v1 ve v2 arasinda bağ varsa true ,aksi false
     * @throws IllegalArgumentException eger v1 veya v2 graph da yoksa
     */
    public static boolean is_connected (Graph graph  ,int v1 ,int v2) throws IllegalArgumentException{
        try{
            int[] arr = breadthFirstSearch(graph, v1);
            if (arr[v2] == -1) return false;
            else return  true;
        }
        catch (Exception ex){
            throw new IllegalArgumentException();
        }
    }

    /**
     * graph uzerinde v1 ve v2 arasinda ki en kisa yolu bulan method
     * @param graph v1 ve v2 nin üzerinde oldugu methodun uzerinde calisicagi graph
     * @param V1 yola cikilacak node
     * @param V2 hedef node
     * @param distance v1 ve v2 arasinda path varsa onun uzerinde edge'lerin weightler toplami.yoksa ınfinity
     * @return v1 ve v2 arasinda path varsa path uzerinde ki vertexler, yoksa null
     */
    public static Vector<Integer> shortest_path(Graph graph, int V1 , int V2 , double[]  distance) {

        int[]predParam = new int[graph.getNumV()];
        double[] distParam  = new double[graph.getNumV()];

        dijkstrasAlgorithm(graph,V1,predParam,distParam);

        distance[0] =  distParam[V2];

        Vector<Integer> returnValue = new Vector<>();

        int preVertex;
        preVertex = predParam[V2];
        returnValue.add(V2);

        while(true){
            returnValue.add(preVertex);
            if(preVertex == V1)
                break;

            else
                preVertex = predParam[preVertex];
        }

        Collections.reverse(returnValue);
        if(distance[0] == Infinity){
            return null;
        }
        return returnValue;
    }



    /**
     * alinan graph objesini ekrana su formatta basar:
     *    Eger bir x vertex'indan y ve z vertexlerine  a ve b agirlikli edgeler var cıktı soyle olur :
     *
     *   'x' vertex'inden cıkan baglantılar :
     *   x : ---a---> : y
     *   x : -------b-----> : z
     *
     *   eger edgeler agirliksiz olsaydı :
     *
     *   'x' vertex'inden cıkan baglantılar :
     *   x :  : y
     *   x :  : z
     *
     *   benzeri output olucakti.
     *
     * @param graph outpu olarak verilecek Graph objesi
     */
    public static void plot_graph(Graph graph) {

        Iterator<Edge> iter;
        int check;

        for (int i = 0; i < graph.getNumV(); i++) {
            iter = graph.edgeIterator(i);
            check= 0;
            while (iter.hasNext()) {
                if(check == 0){
                    System.out.printf("\n\'%d\' vertex'inden çıkan bağlantılar :  \n",i);
                    check = 1;
                }
                Edge temp = iter.next();
                System.out.print(temp.getSource() + " : ");
                if(temp.getWeight() >1.0) {
                    for (int j = 0; j < temp.getWeight(); j++) {

                        System.out.print("-");
                        if (j == (int) temp.getWeight() / 2) {
                            System.out.print(temp.getWeight());
                        }
                    }
                }

                if(graph.isDirected())System.out.print("> ");
                System.out.println(" : "+temp.getDest());
            }

        }
    }

    /**
     * graph'in icinde bit cycle olup olmadigini kontrol eden method.
     * Bu meyhod her vertex icin asil islemi yapan is_acylcle_graph(Graph graph,int start)
     * methodunu cagirir. Eger bir cycle yakalarsa direk eder.
     * @param graph işlem yapılacak Graph objesi
     * @return cycle olup olmama durumu
     */
    public static boolean is_acylcle_graph(Graph graph){
        for(int i = 0 ; i< graph.getNumV();i++){
            if(is_acylcle_graph(graph,i)){
                return true;
            }
        }
        return false;
    }

    /**
     * BFS methodu temel alınarak yazdığım gelen baslangic vertex'i ile bir cycle olup
     * olmadigini kontrol eden method.
     * @param graph uzerinde islem yapilacak method
     * @param start cycle icin bfs nin baslayacagi vertex
     * @return start ile bir cycle yakalayip yakalamama durumu
     */
    private static boolean is_acylcle_graph(Graph graph,int start){

        Queue < Integer > theQueue = new LinkedList < Integer > ();

        boolean[] identified = new boolean[graph.getNumV()];

        identified[start] = true;
        theQueue.offer(start);

        while (!theQueue.isEmpty()) {
            int current = theQueue.remove();
            Iterator < Edge > itr = graph.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();

                // cycyle yakalama durumu
                if (!identified[neighbor] || current == start) {
                    identified[neighbor] = true;
                    theQueue.offer(neighbor);

                    if(current == start) {
                        double[] dis = new double[1];
                        Vector<Integer> vec = shortest_path(graph, start, neighbor, dis);

                        if (graph.isEdge(neighbor, start)) {
                            if (!is_undirected(graph)) {
                                return false;
                            } else if (dis[0] >= 3.0) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
