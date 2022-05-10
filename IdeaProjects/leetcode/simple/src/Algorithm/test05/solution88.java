package Algorithm.test05;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/17  19:49
 */
public class solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int x1 = m - 1;
        int x2 = n - 1;
        int current = m + n - 1;
        while (x1 >= 0 || x2 >= 0){
            if (x1 == -1){
                nums1[current--] = nums2[x2--];
            }else if (x2 == -1){
                nums1[current--] = nums1[x1--];
            }else if (nums1[x1] <= nums2[x2]){
                nums1[current--] = nums2[x2--];
            }else {
                nums1[current--] = nums1[x1--];
            }
        }
    }

    @Test
    public void test() {
        merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3);
    }
}
