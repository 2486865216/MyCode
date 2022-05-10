package Offer.day02;

import Offer.Utils.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/3/19  19:04
 */
public class Solution {
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(0, head.val);
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }
}
