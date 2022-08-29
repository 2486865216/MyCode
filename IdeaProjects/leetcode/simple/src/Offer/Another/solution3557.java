package Offer.Another;

/**
 * author ye
 * createDate 2022/5/8  19:20
 */
public class solution3557 {
    public void merge(int[] A, int m, int[] B, int n) {
        //int j;
        //for (int i = 0; i < n; i++) {
        //    for (j = m + i; j > 0; j--) {
        //        if (A[j - 1] > B[i]){
        //            A[j] = A[j - 1];
        //        }else {
        //            break;
        //        }
        //    }
        //    A[j] = B[i];
        //}
        int a = 0;
        int b = 0;
        int index = 0;
        int[] cur = new int[m + n];
        while (a < m || b < n){
            if (a == m){
                cur[index++] = B[b++];
            }else if (b == n){
                cur[index++] = A[a++];
            }else if (A[a] <= B[b]){
                cur[index++] = A[a];
                a++;
            }else {
                cur[index++] = B[b];
                b++;
            }
        }
        for (int i = 0; i < cur.length; i++) {
            A[i] = cur[i];
        }
    }
}
