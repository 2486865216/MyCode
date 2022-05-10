package Offer.Another;

import Offer.Utils.ListNode;

import java.util.PriorityQueue;

/**
 * author ye
 * createDate 2022/5/8  19:50
 */
public class solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode list : lists) {
            if (list != null){
                queue.add(list);
            }
        }
        ListNode node = new ListNode(0);
        ListNode node1 = node;
        while (!queue.isEmpty()){
            ListNode curNode = queue.poll();;
            node.next = curNode;
            node = node.next;
            if (curNode.next != null){
                queue.add(curNode.next);
            }
        }
        return node1.next;
    }
}
