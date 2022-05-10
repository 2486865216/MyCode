package Algorithm.test07;

/**
 * author ye
 * createDate 2022/3/15  19:43
 */
public class solution136 {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single = single ^ num;
        }
        return single;
    }
}
