package Algorithm.test02;

public class solution29 {
    public int divide(int dividend, int divisor) {
        if (divisor == 1){
            return dividend;
        }
        if (divisor == -1 && dividend == Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        if (dividend < 0 && divisor < 0){
            return divide( -(long) dividend, -(long) divisor);
        }else if (dividend < 0 || divisor < 0){
            return -divide(Math.abs((long)dividend), Math.abs((long)divisor));
        }else {
            return divide( (long) dividend, (long) divisor);
        }
    }
    public int divide(long dividend, long divisor){
        if (dividend < divisor) return 0;
        int count = 1;
        long sum = divisor;
        while (dividend >= sum){
            sum <<= 1;
            count <<= 1;
        }
        sum >>>= 1;
        count >>>= 1;
        return count + divide(dividend - sum, divisor);
    }
}
