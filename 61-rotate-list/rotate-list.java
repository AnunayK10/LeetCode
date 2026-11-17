/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // 1. Find the length of the list and connect the tail to the head to form a cycle
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        
        // Normalize k in case k is greater than the length of the list
        k = k % len;
        if (k == 0) {
            return head;
        }
        
        tail.next = head; // Make it a circular linked list
        
        // 2. Find the new tail: (len - k - 1) steps from the head
        ListNode newTail = head;
        for (int i = 0; i < len - k - 1; i++) {
            newTail = newTail.next;
        }
        
        // 3. Break the cycle to form the new head
        ListNode newHead = newTail.next;
        newTail.next = null;
        
        return newHead;
    }
}