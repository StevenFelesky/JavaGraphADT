public class Main {

    public static void main(String[] args){

        Graph<Integer> myGraph = new Graph<>();

        Node<Integer> node1 = new Node<>(1);

        Node<Integer> node2 = new Node<>(2);

        Node<Integer> node3 = new Node<>(3);

        Node<Integer> node4 = new Node<>(4);

        myGraph.addNode(node1);
        myGraph.addNode(node2);
        myGraph.addNode(node3);
        myGraph.addNode(node4);

        myGraph.addUnDiEdge(node1, node1);
        myGraph.addUnDiEdge(node1, node2);
        myGraph.addUnDiEdge(node1, node3);
        myGraph.addUnDiEdge(node1, node4);

        System.out.println(myGraph.toString());

    }
}
