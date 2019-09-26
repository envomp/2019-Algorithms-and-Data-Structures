import java.util.*;

class MaxHeap {
    ArrayList<Node<Integer>> treeAsArray = new ArrayList<>();

    void enqueue(int element) {
        Node<Integer> node = new Node<>(element);
        treeAsArray.add(node);
        for (int i = log2(treeAsArray.size()); i > 0; i--) {
            balance();
        }
        reAddNodes();
    }

    void dequeue(int toBeRemoved) {
        Collections.swap(treeAsArray, toBeRemoved, treeAsArray.size() - 1);
        treeAsArray.remove(treeAsArray.size() - 1);
        for (int i = log2(treeAsArray.size()); i > 0; i--) {
            balance();
        }
        reAddNodes();
    }

    void balance() {
        int reLocate = treeAsArray.size();
        for (int i = log2(treeAsArray.size()); i > 0; i--) {
            int parent = reLocate >> 1;
            if (treeAsArray.get(reLocate - 1).data > treeAsArray.get(parent - 1).data) {
                System.out.println(String.format("Swapped > %d, %d", treeAsArray.get(reLocate - 1).data, treeAsArray.get(parent - 1).data));
                Collections.swap(treeAsArray, reLocate - 1, parent - 1);
                reLocate = parent;
            }
        }
    }

    private void reAddNodes() {
        for (int j = 0; j < treeAsArray.size(); j++) {
            int i = j + 1;
            treeAsArray.get(j).left = null;
            treeAsArray.get(j).right = null;
            if (treeAsArray.size() >= i << 1) {
                treeAsArray.get(j).left = treeAsArray.get((i << 1) - 1);
            }

            if (treeAsArray.size() >= (i << 1) + 1) {
                treeAsArray.get(j).right = treeAsArray.get(i << 1);
            }
        }
    }

    private int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }
}

class BinarySearchTree {

    Node<Integer> root;

    private Node insertRec(Node root, int key, Node parent) {

        if (root == null) {
            root = new Node<>(key);
            root.parent = parent;
            return root;
        }

        if (key < root.getData())
            root.left = insertRec(root.left, key, root);
        else if (key > root.getData())
            root.right = insertRec(root.right, key, root);
        else {
            return root;
        }

        return balance(root, key);

    }

    private Node balance(Node<Integer> root, int key) {
        /* 2. Update height of this ancestor node */
        root.height = 1 + Math.max(height(root.left), height(root.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < root.left.data)
            return rightRotate(root);

        // Right Right Case
        if (balance < -1 && key > root.right.data)
            return leftRotate(root);

        // Left Right Case
        if (balance > 1 && key > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && key < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        /* return the (unchanged) node pointer */

        return root;
    }

    Node insert(int element) {
        System.out.println(String.format("Added %d", element));
        root = insertRec(root, element, root);
        BTreePrinter.printNode(root);

        return root;
    }

    // A utility function to get the height of the tree
    private int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    private Integer getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Node deleteRec(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < (Integer) root.data)
            root.left = deleteRec(root.left, key);
        else if (key > (Integer) root.data)
            root.right = deleteRec(root.right, key);

        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right).data;

            root.right = deleteRec(root.right,(Integer) root.data);
        }

        return root;
    }

    private static Node minValue(Node root) {
        Node<Integer> minv = root;
        while (root.left != null) {
            minv = root.left;
            root = root.left;
        }
        return minv;
    }

    ArrayList<Integer> getSortedList(Node<Integer> root) {
        ArrayList<Integer> sortedList = new ArrayList<>();
        while (root != null) {

            if (root.left != null && !sortedList.contains(root.left.data)) {
                root = root.left;
                continue;
            }
            if (root.right != null && !sortedList.contains(root.right.data)) {

                if (!sortedList.contains(root.data)) {
                    sortedList.add(root.data);
                }
                root = root.right;
                continue;
            }
            if (!sortedList.contains(root.data)) {
                sortedList.add(root.data);
            }
            root = root.parent;
        }
        return sortedList;
    }

}

public class Lesson_2 {

    private static void one() {

        MaxHeap maxHeap = new MaxHeap();
        maxHeap.treeAsArray = new ArrayList<>();

        for (int element : new int[]{15, 9, 24, 48, 21}) {

            System.out.println(String.format("Added %d", element));
            maxHeap.enqueue(element);
            System.out.println(maxHeap.treeAsArray);
            BTreePrinter.printNode(maxHeap.treeAsArray.get(0));
        }
        System.out.println(String.format("Removed %d", maxHeap.treeAsArray.get(0).data));
        maxHeap.dequeue(0);
        System.out.println(maxHeap.treeAsArray);
        BTreePrinter.printNode(maxHeap.treeAsArray.get(0));

        for (int element : new int[]{25}) {

            System.out.println(String.format("Added %d", element));
            maxHeap.enqueue(element);
            System.out.println(maxHeap.treeAsArray);
            BTreePrinter.printNode(maxHeap.treeAsArray.get(0));
        }

    }

    private static void two() {

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int element : new int[]{41, 79, 38, 31, 45}) {

            binarySearchTree.insert(element);
        }

        System.out.println(String.format("Removed %d", binarySearchTree.root.data));
        binarySearchTree.root = binarySearchTree.deleteRec(binarySearchTree.root, binarySearchTree.root.data);
        BTreePrinter.printNode(binarySearchTree.root);

        for (int element : new int[]{44, 24, 67, 23, 94, 5, 4}) {

            binarySearchTree.insert(element);
        }

        System.out.println(binarySearchTree.getSortedList(binarySearchTree.root));

    }


    private static void three() {

        for (String testCase : new String[]{"*{*[*(*)*]*(*)*}*(*)*", "*)*[*]*(*", "*[*(*]*)*", "*(*)*{{*{*}*(*)*"}) {

            System.out.println(brackers(testCase));

        }
    }

    private static boolean brackers(String testCase) {
        Stack<Integer> stack = new Stack<>();
        Map<String, Integer> map1 = Map.of("{", 1, "[", 2, "(", 3);
        Map<String, Integer> map2 = Map.of("}", 1, "]", 2, ")", 3);
        try {

            for (char i : testCase.toCharArray()) {
                if (map2.containsKey(String.valueOf(i))) {
                    if (!stack.pop().equals(map2.get(String.valueOf(i)))) return false;
                } else if (map1.containsKey(String.valueOf(i))) {
                    stack.add(map1.get(String.valueOf(i)));
                }
            }
        } catch (EmptyStackException e) {
            return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        //one();
        two();
        //three();
    }

}

class Node<T extends Comparable<?>> {
    Node<T> left, right, parent;
    T data;
    int height;

    Node(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return data.toString();
    }

    Integer getData() {
        return (Integer) data;
    }
}

class BTreePrinter {

    static <T extends Comparable<?>> void printNode(Node<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (Node<T> node : nodes) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (node == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (node.left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (node.right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
