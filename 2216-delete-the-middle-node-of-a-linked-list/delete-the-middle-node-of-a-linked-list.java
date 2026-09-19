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
    public ListNode deleteMiddle(ListNode head) {
        // Base case: If there is only one node, deleting the middle means returning null
        if (head == null || head.next == null) {
            return null;
        }
        
        // Initialize slow and fast pointers.
        // We start fast at head.next.next so that the slow pointer stops 
        // EXACTLY one node before the middle node.
        ListNode slow = head;
        ListNode fast = head.next.next;
        
        // Traverse the linked list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Delete the middle node by skipping it
        slow.next = slow.next.next;
        
        return head;
    }
}