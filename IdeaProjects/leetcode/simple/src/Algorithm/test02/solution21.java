package Algorithm.test02;


public class solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode listAll = new ListNode();
        ListNode head = listAll;
        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
                listAll.next = list1;
                list1 = list1.next;
            }else if (list1.val > list2.val){
                listAll.next = list2;
                list2 = list2.next;
            }
            listAll = listAll.next;
        }
        if (list1 == null){
            listAll.next = list2;
        }else{
            listAll.next = list1;
        }
        return head.next;
    }
}
