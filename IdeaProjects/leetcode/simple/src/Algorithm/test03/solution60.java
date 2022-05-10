package Algorithm.test03;

import org.junit.Test;

public class solution60 {
    public String getPermutation(int n, int k) {
        int[] array = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        boolean[] visited = new boolean[n];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int current = array[i];
            for (int j = 1; j <= n; j++) {
                if (visited[j - 1]){
                    continue;
                }
                if (k > current){
                    k = k - current;
                    continue;
                }
                stringBuilder.append(j);
                visited[j - 1] = true;
                break;
            }
        }
        return stringBuilder.toString();
    }

    @Test
    public void test() {
        System.out.println(getPermutation(5 ,46));
    }
}
