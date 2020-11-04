public class Node<T> {

    private T val;

    public Node() { }

    public Node(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public String toString() {
        return val.toString();
    }
}
