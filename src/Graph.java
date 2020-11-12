import java.util.*;

public class Graph<T> {

    Map<Node<T>, List<Node<T>>> adjMap;

    private long numEdges = 0;

    public Graph() {
        adjMap = new HashMap<>();
    }

    public long getNumEdges() { return numEdges; }

    public boolean addNode(Node<T> node) {
        if (!adjMap.containsKey(node)) {
            adjMap.put(node, new LinkedList<>());
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

    public void dfs(Node<T> node) {
        node.visit();

        List<Node<T>> neighbors = adjMap.get(node);

        if (neighbors == null) { return; }

        for (Node<T> neighbor : neighbors) {
            if (!neighbor.isVisited()){
                dfs(neighbor);
            }
        }
    }

    public void fillOrder(int s, boolean[] visited, Stack stack) {
        visited[s] = true;


    }

    public Graph<T> transpose() {
        Graph<T> tGraph = new Graph<>();

        for (Node<T> t : this.adjMap.keySet()) {
            for (Node<T> n : this.adjMap.get(t)) {
                List<Node<T>> sn = tGraph.adjMap.computeIfAbsent(n, k -> new LinkedList<Node<T>>());
                sn.add(t);
            }
        }
        return tGraph;
    }

    public boolean everyNodeHasAnEdge() {
        for (Map.Entry<Node<T>, List<Node<T>>> entry : adjMap.entrySet()) {
            List<Node<T>> neighbors = entry.getValue();
            if (neighbors.isEmpty()) { return false; }
        }
        return true;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Adjacency List Output\n");
        for (Map.Entry<Node<T>, List<Node<T>>> neighbors : adjMap.entrySet()) {
            output.append(neighbors.getKey().getVal()).append(":").append(neighbors.getValue()).append("\n");
        }
        return output.toString();
    }
}
