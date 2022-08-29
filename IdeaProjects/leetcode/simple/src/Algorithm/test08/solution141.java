package Algorithm.test08;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/20  18:50
 */
public class solution141 {
    public boolean hasCycle(ListNode head) {
        /*List<ListNode> list = new ArrayList();
        while (head != null){
            if (list.contains(head.next)){
                return true;
            }
            list.add(head);
            head = head.next;
        }
        return false;*/
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }
}
