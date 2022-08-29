package Offer.Another;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * author ye
 * createDate 2022/5/8  18:43
 */
public class solution2475 {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (i == 0 || list.get(list.size() - 1)[1] < left){
                list.add(new int[]{left, right});
            }else {
                list.get(list.size() - 1)[1] = Math.max(right, list.get(list.size() - 1)[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
