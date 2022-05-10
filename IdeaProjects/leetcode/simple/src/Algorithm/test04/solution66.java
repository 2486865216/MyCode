package Algorithm.test04;

/**
 * author ye
 * createDate 2022/1/26  19:25
 */
public class solution66 {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if ((digits[i] + 1) % 10 != 0){
                digits[i] = digits[i] + 1;
                return digits;
            }else {
                digits[i] = 0;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
