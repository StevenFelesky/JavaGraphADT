import java.util.*;

/*
Steven Felesky
11.12.2020
CSCI 310 GraphADT
*/

public class Graph<T> {

    Map<Node<T>, Set<Node<T>>> adjMap;

    private long numEdges = 0;

    public Graph() {
        adjMap = new HashMap<>();
    }

    public long getNumEdges() { return numEdges; }

    public boolean addNode(Node<T> node) {
        if (!adjMap.containsKey(node)) {
            adjMap.put(node, new HashSet<>());
            return true;
        }
        return false;
    }

    public boolean addUnDiEdge(Node<T> from, Node<T> to) {
        if (adjMap.containsKey(from) && adjMap.containsKey(to)) {
            if(!(adjMap.get(from).contains(to) && adjMap.get(to).contains(from))) {
                adjMap.get(from).add(to);
                adjMap.get(to).add(from);
                numEdges++;
                return true;
            }
        }
        return false;
    }

    public boolean addDiEdge(Node<T> from, Node<T> to) {
        if (adjMap.containsKey(from) && adjMap.containsKey(to)) {
            if(!adjMap.get(from).contains(to)) {
                adjMap.get(from).add(to);
                numEdges++;
                return true;
            }
        }
        return false;
    }

    public boolean everyNodeHasAnEdge() {
        for (Map.Entry<Node<T>, Set<Node<T>>> entry : adjMap.entrySet()) {
            Set<Node<T>> neighbors = entry.getValue();
            if (neighbors.isEmpty()) { return false; }
        }
        return true;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Adjacency Set Output\n");
        for (Map.Entry<Node<T>, Set<Node<T>>> neighbors : adjMap.entrySet()) {
            output.append(neighbors.getKey().getVal()).append(":").append(neighbors.getValue()).append("\n");
        }
        return output.toString();
    }

    //Compute SCC

    public void dfs(Node<T> node) {
        node.visit();
        System.out.print( " " + node + " ");

        Set<Node<T>> neighbors = adjMap.get(node);

        if (neighbors == null) { return; }

        for (Node<T> neighbor : neighbors) {
            if (!neighbor.isVisited()){
                dfs(neighbor);
            }
        }
    }

    public Graph<T> transpose() {
        Graph<T> tGraph = new Graph<>();

        for (Node<T> t : this.adjMap.keySet()) {
            for (Node<T> n : this.adjMap.get(t)) {
                Set<Node<T>> sn = tGraph.adjMap.computeIfAbsent(n, k -> new HashSet<Node<T>>());
                sn.add(t);
            }
        }
        return tGraph;
    }

    public void fillOrder(Node<T> node, Stack<Node<T>> stack) {
        node.visit();
        Set<Node<T>> neighbors = adjMap.get(node);

        if (neighbors == null) { return; }

        for (Node<T> neighbor : neighbors) {
            if (!neighbor.isVisited()){
                fillOrder(neighbor, stack);
            }
        }
        stack.push(node);
    }

    public String SCC() {
        Stack stack = new Stack();
        ArrayList<Node<T>> scc = new ArrayList<>();
        String s = "";

        for (Map.Entry<Node<T>, Set<Node<T>>> entry : adjMap.entrySet()) {
            Node<T> node = entry.getKey();
            node.devisit();
        }

        for (Map.Entry<Node<T>, Set<Node<T>>> entry : adjMap.entrySet()) {
            Node<T> node = entry.getKey();
            if (!node.isVisited()) {
                fillOrder(node, stack);
            }
        }

        Graph<T> gt = this.transpose();

        for (Map.Entry<Node<T>, Set<Node<T>>> entry : adjMap.entrySet()) {
            Node<T> node = entry.getKey();
            node.devisit();
        }

        while (!stack.empty()) {
            Node<T> node = (Node<T>) stack.pop();

            if (!node.isVisited()) {
                System.out.print("{");
                gt.dfs(node);
                System.out.print("} ");
            }
        }
        return s;
    }
}
