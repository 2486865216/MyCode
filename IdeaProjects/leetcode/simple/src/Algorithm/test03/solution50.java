package Algorithm.test03;

public class solution50 {
    public double myPow(double x, int n) {
        long N  = n;
        return N > 0 ? quickPow(x, N) : 1.0 / quickPow(x, -N);
    }
    private double quickPow(double x, long N){
        double ans = 1;
        while (N > 0){
            if (N % 2 ==1){
                ans = ans * x;
            }
            x = x * x;
            N = N / 2;
        }
        return ans;
    }
}
