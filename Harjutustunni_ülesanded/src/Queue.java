import java.util.Stack;

public class Queue {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    void enqueue(Integer i) {
        stack1.add(i);
    }

    Integer dequeue(Integer i) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        Integer a = stack2.pop();

        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return a;
    }

}

class Node1 {
    Integer data;
    Node1 next;
}

class SinglyLinkedList {
    Node1 first;
    int length;
}

class LinkedListStuff {
    void swapToFirst(SinglyLinkedList list, Node1 node1) {

        if (list.length == 0) {
            list.first = node1;
        } else {
            Node1 second = list.first.next;
            Node1 temp = list.first;
            while (true) {
                if (temp.next == node1) {
                    list.first.next = temp.next.next;
                    temp.next = list.first;
                    list.first = temp.next;
                    list.first.next = second;
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }

    }
}

class NodeDouble {
    Integer data;
    NodeDouble next;
    NodeDouble prev;
}

class DoublyLinkedList {
    NodeDouble first;
    NodeDouble last;
    int length;
}

class LinkedListStuff2 {
    void swapToFirst(DoublyLinkedList list, NodeDouble node1) {

        if (list.length <= 1) {
            return;
        } else {
            NodeDouble second = list.first.next;

            NodeDouble one = list.first;
            NodeDouble before = node1.prev;
            NodeDouble after = node1.next;

            node1.prev = null;
            list.first = node1;
            node1.next = second;
            second.prev = node1;

            before.next = one;
            one.prev = before;
            after.prev = one;
            one.next = after;


        }
    }
}