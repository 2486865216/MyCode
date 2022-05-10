package Algorithm.test06;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/3/5  19:44
 */
public class solution119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> cur  = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i){
                    cur.add(1);
                }else {
                    cur.add(list.get(j - 1) + list.get(j));
                }
            }
            list = cur;
        }
        return list;
    }
}
