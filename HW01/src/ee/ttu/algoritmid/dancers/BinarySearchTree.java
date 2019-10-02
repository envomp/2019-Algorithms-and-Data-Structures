package ee.ttu.algoritmid.dancers;

import java.util.ArrayList;

class BinarySearchTree {

    private Node root;

    Node insert(Dancer element) {
        System.out.println(String.format("Added %d", element.getHeight()));
        root = insertRec(root, element, root);
//        root = balance(root, element.getHeight()); //Doesn't do anything.. probably..
        return root;
    }

    Node remove(Node node, int element) {
        System.out.println(String.format("Removed %d", element));
        root = deleteRec(node, element);
        if (root != null) {
            root.printTree();
            root = balance(root, element);
        }

        return root;
    }

    public Dancer getMatch(Dancer dancer) {
        if (root == null) {
            return null;
        } else {
            if (dancer.getGender().equals(Dancer.Gender.MALE)) {
                return getMatchForMale(root, dancer.getHeight(), null);
            } else {
                return getMatchForFemale(root, dancer.getHeight(), null);
            }
        }
    }

    private Dancer getMatchForMale(Node root, Integer key, Node lastSuitable) {
        if (root.key <= key - 5) {
            lastSuitable = root;
            if (root.right != null) return getMatchForMale(root.right, key, lastSuitable);
            return popDancer(root);

        } else if (root.left != null) return getMatchForMale(root.left, key, lastSuitable);
        return popDancer(lastSuitable);
    }

    private Dancer getMatchForFemale(Node root, Integer key, Node lastSuitable) {

        if (root.key >= key + 5) {
            lastSuitable = root;
            if (root.left != null) return getMatchForFemale(root.left, key, lastSuitable);
            return popDancer(root);

        } else if (root.right != null) return getMatchForFemale(root.right, key, lastSuitable);
        return popDancer(lastSuitable);
    }

    private Dancer popDancer(Node root) {
        if (root == null) {
            return null;
        }
        Dancer dancer = root.data.removeFirst();
        if (root.data.isEmpty()) {
            remove(this.root, root.key);
        }
        return dancer;
    }

    private Node insertRec(Node root, Dancer dancer, Node parent) {
        int key = dancer.getHeight();
        if (root == null) {
            root = new Node(key);
            root.parent = parent;
            root.data.add(dancer);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, dancer, root);
        else if (key > root.key)
            root.right = insertRec(root.right, dancer, root);
        else {
            root.data.add(dancer);
            return root;
        }

        return root;

    }

    private Node balance(Node root, int key) {
        /* 2. Update height of this ancestor node */
        root.height = 1 + Math.max(height(root.left), height(root.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < root.left.key)
            return rightRotate(root);

        // Right Right Case
        if (balance < -1 && key > root.right.key)
            return leftRotate(root);

        // Left Right Case
        if (balance > 1 && key > root.left.key) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && key < root.right.key) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        /* return the (unchanged) node pointer */

        return root;
    }

    private int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

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

    private Integer getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right).key;
            root.data = minValue(root.right).data;

            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    private static Node minValue(Node root) {
        Node minv = root;
        while (root.left != null) {
            minv = root.left;
            root = root.left;
        }
        return minv;
    }

    ArrayList<Node> getSortedList() {
        ArrayList<Node> sortedList = new ArrayList<>();
        inOrder(this.root, sortedList);
//        if (this.root != null) {
//            this.root.printTree();
//        }
        return sortedList;
    }

    private void inOrder(Node node, ArrayList<Node> order) {
        if (node != null) {
            inOrder(node.left, order);
            order.add(node);
            inOrder(node.right, order);
        }
    }

}
