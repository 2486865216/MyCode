package Algorithm.test05;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/18  19:30
 */
public class solution89 {
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add(list.get(j) + head);
            }
            head <<= 1;
        }
        return list;
    }
}
