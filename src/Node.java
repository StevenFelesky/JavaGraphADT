public class Node<T> {

    private T val;
    private boolean visited;

    Node() { }

    Node(T val) {
        this.val = val;
    }

    T getVal() {
        return val;
    }

    void setVal(T val) {
        this.val = val;
    }

    void visit() { visited = true; }

    void devisit() { visited = false; }

    boolean isVisited() {
        if(visited) return true;
        else return false;
    }

    public String toString() {
        return val.toString();
    }

}
