import java.util.*;

public class Graph<T> {

    Map<Node<T>, List<Node<T>>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addNode(Node<T> node) {
        if(!adjList.containsKey(node)) {
            adjList.put(node, new ArrayList<>());
        }
    }

    public void addUnDiEdge(Node<T> from, Node<T> to) {
        if(this.adjList.containsKey(from)) {
            if(!adjList.get(from).contains(to)) {
                adjList.get(from).add(to);
            }
            if(!adjList.get(to).contains(from)) {
                adjList.get(to).add(from);
            }
        }
    }

    public void addDiEdge(Node<T> from, Node<T> to) {
        if(adjList.containsKey(from)) {
            if(!adjList.get(from).contains(to)) {
                adjList.get(from).add(to);
            }
        }
    }

    public void dfs(Graph<T> graph, Node<T> start) {

    }

    public String toString() {
        StringBuilder output = new StringBuilder("Adjacency List Output\n");
        for (Map.Entry<Node<T>, List<Node<T>>> neighbors : adjList.entrySet()) {
            output.append(neighbors.getKey().getVal()).append(":").append(neighbors.getValue()).append("\n");
        }
        return output.toString();
    }
}
