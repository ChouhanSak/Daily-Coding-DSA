/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution 
{
    public ListNode removeElements(ListNode head, int val) 
    {
        // Create a dummy node that sits before the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 'curr' will traverse the list, starting from the dummy node
        ListNode curr = dummy;
        
        while (curr.next != null) 
        {
            if (curr.next.val == val) 
            {
                // Skip the node containing the target value
                curr.next = curr.next.next;
            } else {
                // Only move forward if we didn't remove a node
                curr = curr.next;
            }
        }
        
        // Return the actual head of the modified list
        return dummy.next;
    }
}
