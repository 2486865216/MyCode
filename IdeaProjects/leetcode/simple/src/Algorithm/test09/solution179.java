package Algorithm.test09;

import java.util.Arrays;
import java.util.Comparator;

/**
 * author ye
 * createDate 2022/4/7  19:41
 */
public class solution179 {
    public String largestNumber(int[] nums) {
        /*Integer[] numsArray = new Integer[nums.length];
        for (int i = 0; i < numsArray.length; i++) {
            numsArray[i] = nums[i];
        }
        Arrays.sort(numsArray, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                    long xl = Long.parseLong(o1.toString() + o2.toString());
                    long yl = Long.parseLong(o2.toString() + o1.toString());
                    return (int) (yl - xl);
            }
        });
        if (numsArray[0] == 0){
                return "0";
        }
        StringBuilder res = new StringBuilder();
        for (Integer integer : numsArray) {
            res.append(integer);
        }
        return res.toString();*/
        StringBuilder res = new StringBuilder();
        Arrays.stream(nums).mapToObj(String::valueOf)
                .sorted((x, y) -> (x + y).compareTo(y + x))
                .forEach(res::append);
        return res.charAt(0) == '0' ? "0" : res.toString();
    }
}
