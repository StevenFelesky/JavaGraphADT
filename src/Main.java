import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args){

        //BiPartiteGraph(10, (int)(0.8 * nCr(10,2)));
        //DiGraph(10, (int)(0.8 * nCr(10,2)));
        //UnDiGraph(10, (int)(0.8 * nCr(10,2)));
        //UnDiGraph(100, (int)(0.8 * nCr(100,2)));
        //UnDiGraph(10000, (int)(0.8 * nCr(10000,2)));
        //UnDiGraph(100000, (int)(0.8 * nCr(100000,2)));

        BigInteger IOO = new BigInteger("100000");

        System.out.println(fact(IOO));

    }

    //BiPartite Graph with edges between even and odd nodes
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
        while(numEdges < d/2) {
            numEdges++;

            boolean b = myBipartiteGraph.addDiEdge(nodes.get(rand(1, n)), nodes.get(rand(2, n)));
            while (!b) {
                b = myBipartiteGraph.addDiEdge(nodes.get(rand(1, n)), nodes.get(rand(2, n)));
            }
            boolean b1 = myBipartiteGraph.addDiEdge(nodes.get(rand(2, n)), nodes.get(rand(1, n)));
            while (!b1) {
                b1 = myBipartiteGraph.addDiEdge(nodes.get(rand(2, n)), nodes.get(rand(1, n)));
            }
        }

        System.out.println("Every node has an edge: " + myBipartiteGraph.everyNodeHasAnEdge());

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
            myUnDiGraph.addUnDiEdge(nodes.get(k), nodes.get(n-k));
        }

        long numEdges = n;
        while(numEdges < d) {
            numEdges++;

            boolean b = myUnDiGraph.addUnDiEdge(nodes.get(rand(3, n)), nodes.get(rand(3, n)));
            while (!b) {
                b = myUnDiGraph.addUnDiEdge(nodes.get(rand(3, n)), nodes.get(rand(3, n)));
            }
        }

        System.out.println("Every node has an edge: " + myUnDiGraph.everyNodeHasAnEdge());

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
            myDiGraph.addDiEdge(nodes.get(k), nodes.get(n-k));
        }

        long numEdges = n;
        while(numEdges < d) {
            numEdges++;

            boolean b = myDiGraph.addDiEdge(nodes.get(rand(3, n)), nodes.get(rand(3, n)));
            while (!b) {
                b = myDiGraph.addDiEdge(nodes.get(rand(3, n)), nodes.get(rand(3, n)));
            }
        }

        System.out.println("Every node has an edge: " + myDiGraph.everyNodeHasAnEdge());

        myDiGraph.dfs(zNode);

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

    BigInteger n = new BigInteger("0");

    static BigInteger nCr(BigInteger n, BigInteger r) { return (fact(n).divide(fact(r).multiply(n.subtract(r)))); }

    static BigInteger fact(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) { return BigInteger.ONE; }
        else { return n.multiply(fact(n.subtract(BigInteger.ONE))); }
    }

    static int rand(int i, int n) {
        if(i == 1) { return (int)(Math.random() * (n)) * 2; }
        else if(i == 2) { return (int)(Math.random() * (n)) * 2 + 1; }
        else if(i == 3) { return (int)(Math.random() * (n)); }
        else return 0;
    }
}