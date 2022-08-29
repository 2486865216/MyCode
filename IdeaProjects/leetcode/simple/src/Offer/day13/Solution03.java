package Offer.day13;

/**
 * author ye
 * createDate 2022/3/30  19:09
 */
public class Solution03 {
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        int i = s.length() - 1, j = i;
        while (i >= 0){
            while (i >= 0 && s.charAt(i) != ' ') i--;
            res.append(s.substring(i + 1, j + 1) + " ");
            while (i >= 0 && s.charAt(i) == ' ') i--;
            j = i;
        }
        return res.toString().trim();
        /*StringBuilder res = new StringBuilder();

        String[] split = s.split(" +");
        for (int i = split.length - 1; i >= 0; i--) {
            res.append(split[i]).append(" ");
        }

        return res.toString().trim();*/
    }
}
