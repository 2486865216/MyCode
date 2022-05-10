package Offer.Another;

/**
 * author ye
 * createDate 2022/4/30  19:08
 */
public class solution09 {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNum = 0;
        while (x > revertedNum){
            revertedNum = revertedNum * 10 + x % 10;
            x /= 10;
        }
        return revertedNum == x || revertedNum / 10 == x;
    }
}
