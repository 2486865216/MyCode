package Offer.Another;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author ye
 * createDate 2022/5/8  9:39
 */
public class solution2435 {
    List<List<Integer>> allList = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, path, visited);
        return allList;
    }

    private void dfs(int[] nums, List<Integer> path, boolean[] visited) {
        if (nums.length == path.size()){
            allList.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i])){
                continue;
            }
            path.add(nums[i]);
            visited[i] = true;
            dfs(nums, path, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
