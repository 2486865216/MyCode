package Offer.Another;

/**
 * author ye
 * createDate 2022/4/29  19:23
 */
public class solution392 {
    public boolean isSubsequence(String s, String t) {
        if (t.length() == 0) return true;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(index)){
                index++;
            }
            if (index == t.length()){
                return true;
            }
        }
        return false;
    }
}
