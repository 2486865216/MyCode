package Algorithm.test01;


import static java.util.Arrays.sort;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode lsum = new ListNode(-1,null);
        ListNode head = lsum;
        ListNode q1 = l1;
        ListNode q2 = l2;
        int x=0;
        boolean count = false;
        int i=1,j=1;
        while (q1.next!=null){
            i++;
            q1 = q1.next;
        }
        while (q2.next!=null){
            j++;
            q2 = q2.next;
        }
        if (i>j){
            for (int n = 0; n<(i-j) ; n++){
                q2.next = new ListNode(0,null);
                q2 = q2.next;
            }
        }
        if (i<j){
            for (int n = 0; n<(j-i) ; n++){
                q1.next = new ListNode(0,null);
                q1 = q1.next;
            }
        }
        while (l1!=null && l2!=null){
            if (l1.val + l2.val +x >=10){
                lsum.val = (l1.val + l2.val + x)%10;
                x = (l1.val + l2.val +x)/10;
                count = true;
            }
            else {
                lsum.val = (l1.val + l2.val +x);
                x=0;
                count = false;
            }
            l1 = l1.next;
            l2 = l2.next;
            if (l1!=null && l2!=null) {
                lsum.next = new ListNode(0, null);
                lsum = lsum.next;
            }
        }
        if (count){
            lsum.next = new ListNode(x);
            lsum = lsum.next;
        }
        return head;
    }
    public int lengthOfLongestSubstring(String s) {
        int[] a = new int[128];
        for (int i = 0; i < a.length; i++) {
            a[i] = -1;
        }
        int res = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            start = Math.max(start,a[index]+1);
            res = Math.max(res,i-start+1);
            a[index] = i;
        }
        return res;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums3 = new int[nums1.length + nums2.length];
        double result=0;
        for (int i = 0; i < nums1.length; i++) {
            nums3[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            nums3[i+nums1.length] = nums2[i];
        }
        for (int i = 0; i < nums3.length-1; i++) {
            for (int j = i+1; j < nums3.length; j++) {
                if (nums3[i]>nums3[j]){
                    int temp = nums3[i];
                    nums3[i] = nums3[j];
                    nums3[j] = temp;
                }
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            System.out.println(nums3[i]);
        }
        System.out.println(nums3.length);
        if (nums3.length%2!=0){
            result = nums3[nums3.length/2];
        }else{
            double a = nums3[(nums3.length-1)/2];
            double b = nums3[nums3.length/2];
            result = (a+b)/2;
        }
        return result;
    }
}
