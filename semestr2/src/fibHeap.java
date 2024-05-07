public class fibHeap {
    Node min;
    int size = 0;
    public void insert(int key, Count count) {
        Node newNode = new Node(key);

        if (size == 0) {
            min = newNode;
            count.val ++;
            newNode.set_left(newNode);
            newNode.set_right(newNode);
        } else {
            Node prevRight = min.get_right();
            min.set_right(newNode);
            count.val ++;
            newNode.set_left(min);
            newNode.set_right(prevRight);
            prevRight.set_left(newNode);
        }
        if (newNode.get_key() < min.get_key()) {
            min = newNode;
        }
        size++;
    }


    public Node find(int key, Count count) {
        Node current = min;
        if (current == null) {
            return null;
        }
        do {
            count.val ++;
            if (current.get_key() == key) {
                return current;
            }
            current = current.get_right();
        } while (current != min);
        return findNodeInChildren(key, min, count);
    }

    private Node findNodeInChildren(int key, Node parent, Count count) {
        Node child = parent.get_child();
        while (child != null) {
            if (child.get_key() == (key)) {
                return child;
            }
            Node foundNode = findNodeInChildren(key, child, count);
            if (foundNode != null) {
                return foundNode;
            }

            child = child.get_right();
            count.val++;
        }

        return null;
    }

    private void decrease_key(Node x, int k, Count count) {
        if (k > x.get_key())
            return;
        x.set_key(k);
        Node y = x.get_parent();
        if (y != null && x.get_key() < y.get_key()) {
            cut(x, y, count);
            cascadingCut(y, count);
        }
        if (x.get_key() < min.get_key())
            min = x;
    }

    public void delete(Node x, Count count) {
        decrease_key(x, Integer.MIN_VALUE, count);
        int result = deleteMin(count);
    }

    public int deleteMin(Count count) {
        Node prevMin = min;
        deleteParents(min.get_child(), count);
        mergeLists(min, min.get_child(), count);
        Node n1 = min.get_left();
        Node n2 = min.get_right();
        n1.set_right(n2);
        n2.set_left(n1);
        if (prevMin.get_right() == prevMin) {
            min = null;
            size = 0;
            return prevMin.get_key();
        }
        min = min.get_right();
        consolidate(count);
        size--;
        return prevMin.get_key();
    }

    public void deleteParents(Node x, Count count){
        if (x == null){
            return;
        }
        Node currrent = x;
        do {
            count.val++;
            currrent.set_parent(null);
            currrent = currrent.get_right();

        } while (currrent != x);
    }

    private void mergeLists(Node n1, Node n2, Count count) {
        count.val++;
        if (n2 == null || n1 == null) {
            return;
        }
        Node left = n1.get_left();
        Node right = n2.get_right();
        n2.set_right(n1);
        n1.set_left(n2);
        left.set_right(right);
        right.set_left(left);
    }


    private void cascadingCut(Node y, Count count) {
        Node z = y.get_parent();
        if (z != null) {
            if (!y.get_mark())
                y.set_mark(true);
            else {
                cut(y, z, count);
                cascadingCut(z, count);
            }
        }
    }

    private void cut(Node node, Node parent, Count count) {
        count.val += 1;
        node.get_left().set_right(node.get_right());
        node.get_right().set_left(node.get_left());
        parent.degree--;
        node.set_right(min);
        node.set_left(min.get_left());
        min.set_left(node);
        node.get_left().set_right(node);
        node.set_parent(null);
    }

    public void consolidate(Count count) {
        int[] degrees = new int[getPow()];
        Node current = min;
        Node flag = null;
        while (flag != current) {
            count.val++;
            if (degrees[current.degree] == 0) {
                degrees[current.degree] = 1;
                flag = current;
                current = current.get_right();
            } else if (degrees[current.degree] == 1) {
                current = current.get_right();
            } else {
                Node conflict = findNodeWithDegree(degrees, current.degree, count);
                degrees[current.degree] = 0;
                Node addTo;
                Node adding;
                if (conflict != null && conflict.get_key() < current.get_key()) {
                    addTo = conflict;
                    adding = current;
                } else {
                    addTo = current;
                    adding = conflict;
                }
                Node left = adding.get_left();
                Node right = adding.get_right();
                left.set_right(right);
                right.set_left(left);
                adding.set_left(adding);
                adding.set_right(adding);
                if (addTo.get_child() == null) {
                    addTo.set_child(adding);
                } else {
                    mergeLists(addTo.get_child(), adding, count);
                }
                adding.set_parent(addTo);
                addTo.degree++;
                current = addTo;
                flag = null;
            }
            if (min.get_key() > current.get_key()) {
                min = current;
            }

        }
    }

    private int getPow(){
        int n = size;
        int result = 0;
        while (n !=0) {
            result++;
            n /=2;
        }
        return result;
    }

    private Node findNodeWithDegree(int[] degrees, int degree, Count count) {
        for (int i = degree; i < degrees.length; i++) {
            count.val++;
            if (degrees[i] == 1) {
                return findNodeWithDegree(i, count);
            }
        }
        return null;
    }

    private Node findNodeWithDegree(int degree,Count count) {
        Node current = min;
        do {
            count.val++;
            if (current.degree == degree) {
                return current;
            }
            current = current.get_right();
        } while (current != min);
        return null;
    }


}