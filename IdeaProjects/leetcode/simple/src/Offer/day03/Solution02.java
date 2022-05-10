package Offer.day03;

/**
 * author ye
 * createDate 2022/3/20  18:42
 */
public class Solution02 {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }
}
