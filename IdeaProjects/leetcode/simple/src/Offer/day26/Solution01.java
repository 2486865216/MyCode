package Offer.day26;

/**
 * author ye
 * createDate 2022/4/12  18:33
 */
public class Solution01 {
    public boolean isNumber(String s) {
        if (s.trim().length() == 0) return false;
        char[] chars = s.trim().toCharArray();

        boolean isNum = false, isDian = false, isE = false;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9'){
                isNum = true;
            }else if(chars[i] == '.'){
                if (isDian || isE) return false;
                isDian = true;
            }else if(chars[i] == 'e' || chars[i] == 'E'){
                if (!isNum || isE) return false;
                isE = true;
                isNum = false;
            }else if(chars[i] == '+' || chars[i] == '-'){
                if (i != 0 && chars[i - 1] != 'e' && chars[i - 1] != 'E') return false;
            }else return false;
        }
        return isNum;
    }
}
