package Algorithm.test02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author ye
 * createDate 2022/4/23  18:10
 */
public class solution40 {
    List<List<Integer>> lists = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int i = 0; i < candidates.length; i++) {
            System.out.println(candidates[i]);
        }
        dfs(candidates, target, 0);
        return lists;
    }
    public void dfs(int[] candidates, int target, int begin){
        if (0 == target){
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int j = begin; j < candidates.length; j++) {
            if (j > begin && candidates[j] == candidates[j - 1]) continue;
            if (target - candidates[j] >= 0){
                list.add(candidates[j]);
                dfs(candidates, target - candidates[begin], j + 1);
                list.remove(list.size() - 1);
            }else {
                break;
            }
        }
    }

    @Test
    public void test() {
        combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
    }
}
