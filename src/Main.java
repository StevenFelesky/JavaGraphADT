import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

/*
Steven Felesky
11.12.2020
CSCI 310 GraphADT
*/

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

        System.out.println("Problem 1: Create Undirected Graphs\n");

        //Generate the undirected graphs
        UnDiGraph(10, size10);
        //UnDiGraph(100, size100);
        //UnDiGraph(10000, size10000);
        //Unable to generate a graph of this size. OutOfMemoryError
        //UnDiGraph(100000, size100000);

        System.out.println("\n");

        /*
        Problem 2: Compute the strongly connected components of the graphs below. This is done
        int the graph class via printSCC(). printSCC uses an implementation of Kosaraju's algorithm.
        */

        System.out.println("Problem 2: Strongly Connected Components\n");

        //Generate the undirected graphs of size nC2 and computes the strongly connected components of said graphs.
        //DiGraph(10, size10);
        //DiGraph(100, size100);
        //DiGraph(10000, size10000);
        //Unable to generate a graph of this size. OutOfMemoryError
        //DiGraph(100000, 100000);

        System.out.println("\n");

        /*
        Problem 3 :Create/test graphs to see if they are bipartite by enumerating the nodes. I have
        included a method to generate bipartite graphs of size nC2 and a method to enumerate the nodes
        of an existing graph to test if the graph is bipartite.
        */

        System.out.println("Problem 3: Bipartite Graphs\n");

        //Generate bipartite graphs of size nC2.
        //BiPartiteGraph(10, size10);
        //BiPartiteGraph(100, size100);
        //BiPartiteGraph(10000, size10000);
        //Unable to generate a graph of this size. OutOfMemoryError
        //BiPartiteGraph(100000, size100000);

        //Generate graphs with random edges, enumerate with random values < n, and test graphs to check whether they are bipartite.
        //EnumerateDiGraphBipartite(10, size10);
        //EnumerateDiGraphBipartite(100, size100);
        //EnumerateDiGraphBipartite(10000, size10000);
        //Unable to generate a graph of this size. OutOfMemoryError
        //EnumerateDiGraphBipartite(100000, size100000);

    }

    //BiPartite Graph with edges between even and odd nodes, output printed to file.
    public static void BiPartiteGraph(long n, long d) {
        PrintWriter writer;

        Graph<Long> myGraph = new Graph<>();

        //Map nodes by value given in below loop. Used assign random edges.
        Map<Long, Node<Long>> nodes = new HashMap<>();

        //Assign values to nodes and store their values in the map 'nodes'.
        for(long i = 0; i < n; i++){
            Node<Long> node = new Node<>(i);
            myGraph.addNode(node);
            nodes.put(i, node);
        }

        //Adds edges to graph
        while(myGraph.getNumEdges() < d) {
            //Adds random edge from an even node to an odd node.
            boolean b = myGraph.addDiEdge(nodes.get(rand(1, n)), nodes.get(rand(2, n)));
            while (!b) {
                b = myGraph.addDiEdge(nodes.get(rand(1, n)), nodes.get(rand(2, n)));
            }
            //Adds random edge from an odd node to an even node.
            boolean b1 = myGraph.addDiEdge(nodes.get(rand(2, n)), nodes.get(rand(1, n)));
            while (!b1) {
                b1 = myGraph.addDiEdge(nodes.get(rand(2, n)), nodes.get(rand(1, n)));
            }
        }

        System.out.println("Every node has an edge: " + myGraph.everyNodeHasAnEdge());
        System.out.println("Graph is bipartite: " + evenOddBipartiteTest(myGraph));
        System.out.println("Number of edges = " + myGraph.getNumEdges());

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

    //Generates un-enumerated graph of size n and randomly enumerates it.
    public static void EnumerateDiGraphBipartite(long n, long d) {
        PrintWriter writer;

        Graph<Long> myGraph = new Graph<>();

        //Map nodes by value given in below loop. Used assign random edges.
        Map<Long, Node<Long>> nodes = new HashMap<>();

        //does not assign values to nodes but stores their values in the map 'nodes'.
        for(long i = 0; i < n; i++){
            Node<Long> node = new Node<>();
            myGraph.addNode(node);
            nodes.put(i, node);
        }

        //force every node to have an edge
        for(long k = 0; k <= n; k++) {
            myGraph.addDiEdge(nodes.get(k), nodes.get(n-k));
        }

        //randomly assign rest of edges
        while(myGraph.getNumEdges() < d) {
            myGraph.addDiEdge(nodes.get(rand(3, n)), nodes.get(rand(3, n)));
        }

        //assign nodes values as described in problem 3
        for (Map.Entry<Node<Long>, Set<Node<Long>>> entry : myGraph.adjMap.entrySet()) {

            Node<Long> node = entry.getKey();
            Set<Node<Long>> neighbors = entry.getValue();

            //enumerate nodes with random values unless they have already been assigned a value
            //if the graph is bipartite this should not pose an issue
            long i = rand(3, n);
            if (node.getVal() == null) {
                node.setVal(i);
            }

            //assign the neighbors of node odd values if node is even
            if (node.getVal() % 2 == 0) {
                for (Node<Long> x : neighbors) {
                    x.setVal(rand(2, n));
                }
            } //assign the neighbors of node even values if node is odd
            else {
                for (Node<Long> x : neighbors) {
                    x.setVal(rand(1, n));
                }
            }
        }

        System.out.println("Every node has an edge: " + myGraph.everyNodeHasAnEdge());
        System.out.println("Graph is bipartite: " + evenOddBipartiteTest(myGraph));
        System.out.println("Number of edges = " + myGraph.getNumEdges());

        String name = n + "_EnumeratedDiGraphBipartite_output.txt";

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
    //Prints adjacency list output to file.
    public static void UnDiGraph(long n, long d){
        PrintWriter writer;

        Graph<Long> myGraph = new Graph<>();

        //Map nodes by value given in below loop. Used assign random edges.
        Map<Long, Node<Long>> nodes = new HashMap<>();

        //Assign values to nodes and store their values in the map 'nodes'.
        for(long i = 0; i < n; i++){
            Node<Long> node = new Node<>(i);
            myGraph.addNode(node);
            nodes.put(i, node);
        }

        //force every node to have an edge
        for(long k = 0; k < n; k++) {
            myGraph.addUnDiEdge(nodes.get(k), nodes.get(n-k));
        }

        //randomly assign rest of edges
        while(myGraph.getNumEdges() < d) {
            myGraph.addUnDiEdge(nodes.get(rand(3, n)), nodes.get(rand(3, n)));
        }

        System.out.println("Every node has an edge: " + myGraph.everyNodeHasAnEdge());
        System.out.println("Number of edges = " + myGraph.getNumEdges());

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
    //Prints adjacency list output to file.
    public static void DiGraph(long n, long d){
        PrintWriter writer;

        Graph<Long> myGraph = new Graph<>();

        //Map nodes by value given in below loop. Used assign random edges.
        Map<Long, Node<Long>> nodes = new HashMap<>();

        //Assign values to nodes and store their values in the map 'nodes'.
        for(long i = 0; i < n; i++){
            Node<Long> node = new Node<>(i);
            myGraph.addNode(node);
            nodes.put(i, node);
        }

        //force every node to have an edge
        for(long k = 0; k <= n; k++) {
            myGraph.addDiEdge(nodes.get(k), nodes.get(n-k));
        }

        //randomly assign rest of edges
        while(myGraph.getNumEdges() < d) {
            myGraph.addDiEdge(nodes.get(rand(3, n)), nodes.get(rand(3, n)));
        }

        //compute strongly connected components of the graph. SCC prints the values to the console.
        System.out.println(myGraph.SCC());

        System.out.println("Every node has an edge: " + myGraph.everyNodeHasAnEdge());
        System.out.println("Number of edges = " + myGraph.getNumEdges());

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

    //tests if a graph is bipartite by every even node only having edges with odd nodes and vice-versa.
    //cannot go in graph class because there may be graphs of different types such as string.
    static boolean evenOddBipartiteTest(Graph<Long> graph) {
        //loop through the hashMap adjMap to get node and their adjacency lists
        for (Map.Entry<Node<Long>, Set<Node<Long>>> entry : graph.adjMap.entrySet()) {

            Set<Node<Long>> neighbors = entry.getValue(); //get adjacency list
            Node<Long> node = entry.getKey(); //get node

            //if an even node has an edge with an even node return false
            if (node.getVal() % 2 == 0) {
                for (Node<Long> neighbor : neighbors) {
                    if (neighbor.getVal() % 2 == 0) { return false; }
                }
            } //if an odd edge has an edge with an odd edge return false
            else {
                for (Node<Long> neighbor : neighbors) {
                    if (neighbor.getVal() % 2 == 1) { return false; }
                }
            }
        }
        return true;
    }


    //return random values. 1 returns an even random value, 2 returns and odd random value
    //and 3 returns any random value. All values returns are between 0 and n.
    static long rand(long i, long n) {
        if(i == 1) { return (long)(Math.random() * (n)) * 2; }
        else if(i == 2) { return (long)(Math.random() * (n)) * 2 + 1; }
        else if(i == 3) { return (long)(Math.random() * (n)); }
        else return 0;
    }

    //calculate combinations
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