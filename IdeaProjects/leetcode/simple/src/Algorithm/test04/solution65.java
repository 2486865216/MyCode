package Algorithm.test04;

/**
 * author ye
 * createDate 2022/1/26  18:58
 */
public class solution65 {
    public boolean isNumber(String s) {
        boolean hasE = false, hasNum = false,isFloat = false;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E') && (s.charAt(i)=='-' || s.charAt(i)=='+')){
                continue;
            }else if ((s.charAt(i)=='e' || s.charAt(i) == 'E') && !hasE && hasNum){
                hasE = true;
                hasNum = false;
            }else if (s.charAt(i) == '.' && !isFloat && !hasE){
                isFloat = true;
            } else if (s.charAt(i) < '9' && s.charAt(i) > '0'){
                hasNum = true;
            }else{
                return false;
            }
        }
        return hasNum;
    }
}
