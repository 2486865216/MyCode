package Algorithm.test04;

/**
 * author ye
 * createDate 2022/1/26  20:24
 */
public class solution69 {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int num = -1;
        while (left <= right){
            long mid = (left + right) / 2;
            if (mid * mid <= x){
                num = (int) mid;
                left = (int) mid + 1;
            }else{
                right = (int) mid - 1;
            }
        }
        return num;
    }
}
