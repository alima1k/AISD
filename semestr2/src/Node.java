
class Node {
    private Node parent;
    private Node left;
    private Node right;
    private Node child;
    int degree;
    private int key;
    private boolean mark;

    public Node() {
        this.degree = 0;
        this.mark = false;
        this.parent = null;
        this.left = this;
        this.right = this;
        this.child = null;
        this.key = Integer.MAX_VALUE;
    }

    Node(int x) {
        this();
        this.key = x;
    }



    void set_parent(Node x) {
        this.parent = x;
    }

    Node get_parent() {
        return this.parent;
    }

    void set_left(Node x) {
        this.left = x;
    }

    Node get_left() {
        return this.left;
    }

    void set_right(Node x) {
        this.right = x;
    }

    Node get_right() {
        return this.right;
    }

    void set_child(Node x) {
        this.child = x;
    }

    Node get_child() {
        return this.child;
    }

    void set_degree(int x) {
        this.degree = x;
    }

    int get_degree() {
        return this.degree;
    }

    void set_mark(boolean m) {
        this.mark = m;
    }

    boolean get_mark() {
        return this.mark;
    }

    void set_key(int x) {
        this.key = x;
    }

    int get_key() {
        return this.key;
    }
}
