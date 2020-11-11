import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args){

    BiPartiteGraph(10, (int)(0.8 * nCr(10,2)));

    }

    //Bi-Partite Graph with edges between even and odd nodes
    public static void BiPartiteGraph(int n, int d) {
        PrintWriter writer;

        Graph<Integer> myBipartiteGraph = new Graph<>();
        Map<Integer, Node<Integer>> nodes = new HashMap<>();
        for(Integer i = 0; i < n; i++){
            Node<Integer> node = new Node<>(i);
            myBipartiteGraph.addNode(node);
            nodes.put(i, node);
        }

        long numEdges = 0;
        while(numEdges > d/2){
            int r = (int)(Math.random() * (n)) * 2;
            int r2 = (int)(Math.random() * (n)) * 2;

            myBipartiteGraph.addDiEdge(nodes.get(r), nodes.get(r2));
            }

        //System.out.println(myGraph.toString());

        String name = n + "_BiPartiteGraph_output.txt";

        try {
            writer = new PrintWriter(name, StandardCharsets.UTF_8);
            writer.print(myBipartiteGraph.toString());
            writer.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    //Creates a undirected graph of size n with d number of edges. 
    //Prints adjacency list output to file.
    public static void UnDiGraph(int n, int d){
        PrintWriter writer;

        Graph<Integer> myUnDiGraph = new Graph<>();
        Map<Integer, Node<Integer>> nodes = new HashMap<>();
        for(Integer i = 0; i < n; i++){
            Node<Integer> node = new Node<>(i);
            myUnDiGraph.addNode(node);
            nodes.put(i, node);
        }

        for(int k = 0; k < n; k++) {
            myUnDiGraph.addUnDiEdge(nodes.get(k), nodes.get((int)(Math.random() * n)));
        }

        for(int j = 0; j < d - n; j++){
            myUnDiGraph.addUnDiEdge(nodes.get((int)(Math.random() * n)), nodes.get((int)(Math.random() * n)));
        }

        //System.out.println(myGraph.toString());

        String name = n + "_UndirectedGraph_output.txt";

        try {
            writer = new PrintWriter(name, StandardCharsets.UTF_8);
            writer.print(myUnDiGraph.toString());
            writer.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    //Creates a graph of size n with d number of edges. 
    //Prints adjacency list output to file.
    public static void DiGraph(int n, int d){
        PrintWriter writer;

        Graph<Integer> myDiGraph = new Graph<>();
        Map<Integer, Node<Integer>> nodes = new HashMap<>();

        Node<Integer> zNode = new Node<>(0);
        myDiGraph.addNode(zNode);
        nodes.put(0, zNode);

        for(Integer i = 1; i < n; i++){
            Node<Integer> node = new Node<>(i);
            myDiGraph.addNode(node);
            nodes.put(i, node);
        }

        for(int k = 0; k < n; k++) {
            myDiGraph.addDiEdge(nodes.get(k), nodes.get((int)(Math.random() * n)));
        }

        for(int j = 0; j < d - n; j++){
            myDiGraph.addDiEdge(nodes.get((int)(Math.random() * n)), nodes.get((int)(Math.random() * n)));
        }

        myDiGraph.dfs(zNode);

        //System.out.println(myGraph.toString());

        String name = n + "_DirectedGraph_output.txt";

        try {
            writer = new PrintWriter(name, StandardCharsets.UTF_8);
            writer.print(myDiGraph.toString());
            writer.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    static int nCr(long n, int r) { return (int)(fact(n) / (fact(r) * fact(n - r))); }

    static long fact(long n) { return n <= 1 ? 1 : n*fact(n-1); }
}