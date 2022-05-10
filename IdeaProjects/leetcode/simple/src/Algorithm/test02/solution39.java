package Algorithm.test02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class solution39 {
    List<List<Integer>> lists = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0);
        return lists;
    }
    public void dfs(int[] candidates, int target, int i){
        if (i == candidates.length) return;
        if (0 == target){
            lists.add(new ArrayList<>(list));
            return;
        }
        dfs(candidates, target, i + 1);
        if (target - candidates[i] >= 0){
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
}
