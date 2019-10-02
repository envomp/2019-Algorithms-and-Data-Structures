package ee.ttu.algoritmid.dancers;

import java.util.LinkedList;

class Node {
    Node left, right, parent;
    Integer key;
    LinkedList<Dancer> data = new LinkedList<>();
    int height;

    Node(Integer key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "[" + key.toString() + ", " + data.toString() + "]";
    }

    void printTree() {
        System.out.println(printTreeRec(new StringBuilder(), false, new StringBuilder()));
    }

    private StringBuilder printTreeRec(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (right != null) {
            right.printTreeRec(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }

        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(toString()).append("\n");

        if (left != null) {
            left.printTreeRec(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }

        return sb;
    }
}
