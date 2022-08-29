package src.random;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        int[] a = new int[5];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(11);
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
