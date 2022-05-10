package Offer.day12;

import Offer.Utils.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/29  18:33
 */
public class Solution02 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*List<ListNode> listNodes = new ArrayList<>();
        while (headA != null){
            listNodes.add(headA);
            headA = headA.next;
        }
        while (headB != null){
            if (listNodes.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;*/
        ListNode n1 = headA;
        ListNode n2 = headB;
        while (n1 != n2){
            n1 = n1 == null ? headB : n1.next;
            n2 = n2 == null ? headA : n2.next;
        }
        return n1;
        /**
         * 链表 \=headA 和 =headB 的长度分别是 m 和 n。
         * 假设链表 headA 的不相交部分有 a 个节点，链表 headB
         * 的不相交部分有 b 个节点，两个链表相交的部分有 c 个节点，则有 a+c=m，b+c=n。
         *
         * 如果 a=b，则两个指针会同时到达两个链表的第一个公共节点，此时返回两个链表的第一个公共节点；
         *
         * 如果 a != b
         *  则指针 pA 会遍历完链表headA，指针 pB 会遍历完链表headB，
         *  两个指针不会同时到达链表的尾节点，然后指针pA 移到链表headB 的头节点，
         *  指针 pB 移到链表 headA 的头节点，然后两个指针继续移动，
         *  在指针pA 移动了 a+c+b 次、指针 pB 移动了 b+c+a 次之后
         *  两个指针会同时到达两个链表的第一个公共节点，该节点也是两个指针第一次同时指向的节点，
         *  此时返回两个链表的第一个公共节点。
         */
    }
}
