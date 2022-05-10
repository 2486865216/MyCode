package Algorithm.test06;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/3/4  20:29
 */
public class solution118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if (numRows == 0) return list;
        for (int i = 0; i < numRows; i++) {
            List<Integer> array = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i){
                    array.add(1);
                }else {
                    array.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                }
            }
            list.add(array);
        }
        return list;
    }
}
