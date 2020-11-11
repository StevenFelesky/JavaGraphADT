import java.util.*;

public class Graph<T> {

    Map<Node<T>, Set<Node<T>>> adjMap;

    public Graph() {
        adjMap = new HashMap<>();
    }

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
                return true;
            }
        }
        return false;
    }

    public boolean addDiEdge(Node<T> from, Node<T> to) {
        if (adjMap.containsKey(from) && adjMap.containsKey(to)) {
            if(!adjMap.get(from).contains(to)) {
                adjMap.get(from).add(to);
                return true;
            }
        }
        return false;
    }

    public void dfs(Node<T> node) {
        node.visit();
        System.out.print(node.toString());

        Set<Node<T>> neighbors = adjMap.get(node);

        if (neighbors == null) { return; }

        for (Node<T> neighbor : neighbors) {
            if (!neighbor.isVisited()){
                dfs(neighbor);
            }
        }
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Adjacency List Output\n");
        for (Map.Entry<Node<T>, Set<Node<T>>> neighbors : adjMap.entrySet()) {
            output.append(neighbors.getKey().getVal()).append(":").append(neighbors.getValue()).append("\n");
        }
        return output.toString();
    }
}
