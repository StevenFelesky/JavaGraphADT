import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Main {

    //Part 1: Generate undirected graphs of size n with n^2/2 edges.
    //Results are printed to file with the name "n_output.txt"
    public static void main(String[] args){

        //UnDiGraph(10, 45);
        //UnDiGraph(100, 4500);
        //UnDiGraph(10000, 450000);
        //UnDiGraph(100000, 4500000);

        //DiGraph(10, 45);
        //DiGraph(100, 4500);
        //DiGraph(10000, 450000);
        //DiGraph(100000, 4500000);

        BiPartiteGraph(10, 45);
        BiPartiteGraph(100, 4500);
        BiPartiteGraph(10000, 450000);
        BiPartiteGraph(100000, 4500000);
        
    }

    //Bi-Partite Graph with edges between even and odd nodes
    public static void BiPartiteGraph(int n, int d) {
        PrintWriter writer;

        Graph<Integer> myGraph = new Graph<>();
        Map<Integer, Node<Integer>> nodes = new HashMap<>();
        for(Integer i = 0; i < n; i++){
            Node<Integer> node = new Node<>(i);
            myGraph.addNode(node);
            nodes.put(i, node);
        }

        for(int j = 0; j < (d/2); j++){
            myGraph.addDiEdge(nodes.get((int)(Math.random() * (n/2)) * 2), nodes.get((int)(Math.random() * (n/2)) * 2 + 1));
            myGraph.addDiEdge(nodes.get((int)(Math.random() * (n/2)) * 2 + 1), nodes.get((int)(Math.random() * (n/2)) * 2));
        }

        //System.out.println(myGraph.toString());

        String name = n + "_BiPartiteGraph_output.txt";

        try {
            writer = new PrintWriter(name, StandardCharsets.UTF_8);
            writer.print(myGraph.toString());
            writer.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    //Creates a undirected graph of size n with d number of edges. 
    //Prints adjecency list output to file.
    public static void UnDiGraph(int n, int d){
        PrintWriter writer;

        Graph<Integer> myGraph = new Graph<>();
        Map<Integer, Node<Integer>> nodes = new HashMap<>();
        for(Integer i = 0; i < n; i++){
            Node<Integer> node = new Node<>(i);
            myGraph.addNode(node);
            nodes.put(i, node);
        }

        for(int j = 0; j < d; j++){
            myGraph.addUnDiEdge(nodes.get((int)(Math.random() * n)), nodes.get((int)(Math.random() * n)));
        }

        //System.out.println(myGraph.toString());

        String name = n + "_UndirectedGraph_output.txt";

        try {
            writer = new PrintWriter(name, StandardCharsets.UTF_8);
            writer.print(myGraph.toString());
            writer.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    //Creates a graph of size n with d number of edges. 
    //Prints adjecency list output to file.
    public static void DiGraph(int n, int d){
        PrintWriter writer;

        Graph<Integer> myGraph = new Graph<>();
        Map<Integer, Node<Integer>> nodes = new HashMap<>();
        for(Integer i = 0; i < n; i++){
            Node<Integer> node = new Node<>(i);
            myGraph.addNode(node);
            nodes.put(i, node);
        }

        for(int j = 0; j < d; j++){
            myGraph.addDiEdge(nodes.get((int)(Math.random() * n)), nodes.get((int)(Math.random() * n)));
        }

        //System.out.println(myGraph.toString());

        String name = n + "_DirectedGraph_output.txt";

        try {
            writer = new PrintWriter(name, StandardCharsets.UTF_8);
            writer.print(myGraph.toString());
            writer.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}