package Algorithm.test03;

import org.junit.Test;

import java.util.*;

public class solution46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, lists, 0);
        return lists;
    }

    private void backtrack(int n, List<Integer> output, List<List<Integer>> lists, int first) {
        if (first == n){
            lists.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backtrack(n, output ,lists, first + 1);
            Collections.swap(output, first, i);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> lists = permute(new int[]{1, 2, 3});
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
