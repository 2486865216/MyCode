package Algorithm.test03;

/**
 * author ye
 * createDate 2022/4/23  18:53
 */
public class solution45 {
    public int jump(int[] nums) {
        int step = 0;
        int end = 0, maxPosition = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end){
                end = maxPosition;
                step++;
            }
        }
        return step;
    }
}
