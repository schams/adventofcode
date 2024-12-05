public class OrderNode {
    public final int right;
    public final int left;
    public final OrderNode parent;

    public OrderNode(int left, int right, OrderNode parent) {
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
