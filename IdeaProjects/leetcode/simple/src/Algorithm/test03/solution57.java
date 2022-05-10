package Algorithm.test03;

import java.util.ArrayList;
import java.util.List;

public class solution57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean place = true;
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right){
                //在右侧
                if (place){
                    list.add(new int[]{left, right});
                    place = false;
                }
                list.add(interval);
            }else if (interval[1] < left){
                //在左侧
                list.add(interval);
            }else{
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (place){
            list.add(new int[]{left, right});
        }
        return list.toArray(new int[list.size()][]);
    }
}
