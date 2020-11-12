import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args){

        /*
        Problem 1: Create undirected graphs of size 10, 100, 10000, 100000
        with the number of edges being 80% of nC2. The graphs are tested to
        see if every node has at least one edge by the everyNodeHasAnEdge()
        method in the Graph class.
        */

        //Calculate the number of edges, these numbers will be used in all 3 sections.
        BigInteger oneHundred = new BigInteger("100");
        BigInteger tenThousand = new BigInteger("10000");
        BigInteger oneHundredThousand = new BigInteger("100000");

        long size10 = 4 * nCr(BigInteger.TEN, BigInteger.TWO).intValue() / 5;
        long size100 = 4 * nCr(oneHundred, BigInteger.TWO).intValue() / 5;
        long size10000 = 4 * nCr(tenThousand, BigInteger.TWO).intValue() / 5;
        long size100000 = 4 * nCr(oneHundredThousand, BigInteger.TWO).longValue() / 5;

        //Generate the undirected graphs
        //UnDiGraph(10, size10);
        //UnDiGraph(100, size100);
        //UnDiGraph(10000, size10000);
        //Unable to generate a graph of this size. My computer does not have sufficient memory for the Java VM.
        //UnDiGraph(100000, size100000);


        /*
        Problem 2: Compute the strongly connected components of the graphs below.
        */

        //Generate the undirected graphs
        //DiGraph(10, size10);
        //DiGraph(100, size100);
        //DiGraph(10000, size10000);
        //Unable to generate this graph. Java VM runs out of memory working with a graph of this size.
        //DiGraph(100000, size100000);


        /*
        Problem 3 :Create/test graphs to see if they are bipartite by enumerating the nodes. I have
        included a method to generate bipartite graphs of size nC2 and a method to enumerate the nodes
        of an existing graph to test if the graph is bipartite.
        */

        //Generate bipartite graphs of size nC2.
        BiPartiteGraph(10, size10);
        //BiPartiteGraph(100, size100);
        //BiPartiteGraph(10000, size10000);
        //As stated above, Java VM runs out of memory working with a graph of this size.
        //BiPartiteGraph(100000, size100000);

        //Generate, enumarate and test graphs to check whether they are bipartite.


    }

    //BiPartite Graph with edges between even and odd nodes
    public static void BiPartiteGraph(long n, long d) {
        PrintWriter writer;

        Graph<Long> myBipartiteGraph = new Graph<>();
        Map<Long, Node<Long>> nodes = new HashMap<>();

        for(long i = 0; i < n; i++){
            Node<Long> node = new Node<>(i);
            myBipartiteGraph.addNode(node);
            nodes.put(i, node);
        }

        while(myBipartiteGraph.getNumEdges() < d) {

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
        System.out.println("Graph is bipartite: " + evenOddBipartiteTest(myBipartiteGraph));
        System.out.println("Number of edges = " + myBipartiteGraph.getNumEdges());

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
    public static void UnDiGraph(long n, long d){
        PrintWriter writer;

        Graph<Long> myUnDiGraph = new Graph<>();
        Map<Long, Node<Long>> nodes = new HashMap<>();

        for(long i = 0; i < n; i++){
            Node<Long> node = new Node<>(i);
            myUnDiGraph.addNode(node);
            nodes.put(i, node);
        }

        for(long k = 0; k < n; k++) {
            myUnDiGraph.addUnDiEdge(nodes.get(k), nodes.get(n-k));
        }

        while(myUnDiGraph.getNumEdges() < d) {
            myUnDiGraph.addUnDiEdge(nodes.get(rand(3, n)), nodes.get(rand(3, n)));
        }

        System.out.println("Every node has an edge: " + myUnDiGraph.everyNodeHasAnEdge());
        System.out.println("Number of edges = " + myUnDiGraph.getNumEdges());

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
    public static void DiGraph(long n, long d){
        PrintWriter writer;

        Graph<Long> myDiGraph = new Graph<>();
        Map<Long, Node<Long>> nodes = new HashMap<>();

        for(long i = 0; i < n; i++){
            Node<Long> node = new Node<>(i);
            myDiGraph.addNode(node);
            nodes.put(i, node);
        }

        for(long k = 0; k < n; k++) {
            myDiGraph.addDiEdge(nodes.get(k), nodes.get(n-k));
        }

        while(myDiGraph.getNumEdges() < d) {
            myDiGraph.addDiEdge(nodes.get(rand(3, n)), nodes.get(rand(3, n)));
        }

        System.out.println("Every node has an edge: " + myDiGraph.everyNodeHasAnEdge());
        System.out.println("Number of edges = " + myDiGraph.getNumEdges());

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

    //Creates a graph of size n with d number of edges.
    //Prints adjacency list output to file.
    public static void NumerateDiGraph(long n, long d){
        PrintWriter writer;

        Graph<Long> myDiGraph = new Graph<>();

        for(long i = 0; i < n; i++){
            Node<Long> node = new Node<>(i);
            myDiGraph.addNode(node);
        }

        System.out.println("Every node has an edge: " + myDiGraph.everyNodeHasAnEdge());
        System.out.println("Number of edges = " + myDiGraph.getNumEdges());

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

    static boolean evenOddBipartiteTest(Graph<Long> graph) {
        for (Map.Entry<Node<Long>, Set<Node<Long>>> entry : graph.adjMap.entrySet()) {

            Set<Node<Long>> neighbors = entry.getValue();
            Node<Long> node = entry.getKey();

            if (node.getVal() % 2 == 0) {
                for (Node<Long> neighbor : neighbors) {
                    if (neighbor.getVal() % 2 == 0) { return false; }
                }
            }
            else {
                for (Node<Long> neighbor : neighbors) {
                    if (neighbor.getVal() % 2 == 1) { return false; }
                }
            }
        }
        return true;
    }

    static long rand(long i, long n) {
        if(i == 1) { return (long)(Math.random() * (n)) * 2; }
        else if(i == 2) { return (long)(Math.random() * (n)) * 2 + 1; }
        else if(i == 3) { return (long)(Math.random() * (n)); }
        else return 0;
    }

    static BigInteger nCr(BigInteger n, BigInteger r) {
        BigInteger c = new BigInteger("1");
        BigInteger i = new BigInteger("1");

        if (n.subtract(r).compareTo(r) <= 0) {
            r = (n.subtract(r));
        }
        for(; i.compareTo(r) <= 0; i = i.add(BigInteger.ONE)) {
            c = (c.multiply(n.subtract(i).add(BigInteger.ONE))).divide(i);
        }
        return c;
    }
}