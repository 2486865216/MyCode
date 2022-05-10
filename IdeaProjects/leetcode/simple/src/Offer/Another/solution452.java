package Offer.Another;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * author ye
 * createDate 2022/4/29  18:51
 */
public class solution452 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]){
                    return 1;
                }else if (o1[1] < o2[1]){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        int position = points[0][1];
        int ans = 1;
        for (int[] point : points) {
            if (position < point[0]){
                position = point[1];
                ans++;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        findMinArrowShots(new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}});
    }
}
