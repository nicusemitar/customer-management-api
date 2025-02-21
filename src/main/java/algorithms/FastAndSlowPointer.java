package algorithms;

import java.util.HashMap;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class FastAndSlowPointer {

    public static void main(String[] args) {

        // Creating a linked list with a loop
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = head.next.next; // Creating a loop (Node 3)

        ListNode loopStart = detectLoopStart(head);

    }

    public static ListNode detectLoopStart(ListNode head) {
        HashMap<ListNode, Boolean> visited = new HashMap<>();
        ListNode current = head;

        while (current != null) {
            // If node is already visited, it's the loop's starting point
            if (visited.containsKey(current)) {
                return current;
            }
            // Mark the current node as visited
            visited.put(current, true);
            // Move to the next node
            current = current.next;
        }

        return null; // No loop found
    }
}
