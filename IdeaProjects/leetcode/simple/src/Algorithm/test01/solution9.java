package Algorithm.test01;

public class solution9 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int n = 0;
        int x1 = x;
        while (x1 != 0) {
            n = n * 10 + x1 % 10;
            x1 = x1 / 10;
        }
        if (n != x) return false;
        return true;
    }
}
