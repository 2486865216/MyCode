package Offer.Another;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/5/7  19:41
 */
public class solution46 {
    List<List<Integer>> allList = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        dfs(nums, new ArrayList<>());
        return allList;
    }
    public void dfs(int[] nums, List<Integer> path){
        if (nums.length == path.size()){
            allList.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])){
                continue;
            }
            path.add(nums[i]);
            dfs(nums, path);
            path.remove(path.size() - 1);
        }
    }
}
