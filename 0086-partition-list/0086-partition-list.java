class Solution 
{
    public ListNode partition(ListNode head, int x) 
    {
        if (head == null || head.next == null)
        {
            return head;
        }

       
        List<ListNode> lessNodes = new ArrayList<>();
        List<ListNode> greaterNodes = new ArrayList<>();

        
        ListNode current = head;
        while (current != null) 
        {
            if (current.val < x) 
            {
                lessNodes.add(current);
            } else 
            {
                greaterNodes.add(current);
            }
            current = current.next;
        }

       
        ListNode dummy = new ListNode(0);
        ListNode newCurrent = dummy;

        
        for (ListNode node : lessNodes) 
        {
            newCurrent.next = node;
            newCurrent = newCurrent.next;
        }

       
        for (ListNode node : greaterNodes) 
        {
            newCurrent.next = node;
            newCurrent = newCurrent.next;
        }

        newCurrent.next = null;

        return dummy.next;
    }
}