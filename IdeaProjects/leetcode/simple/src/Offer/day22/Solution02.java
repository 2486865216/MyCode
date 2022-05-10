package Offer.day22;

/**
 * author ye
 * createDate 2022/4/8  19:14
 */
public class Solution02 {
    public int singleNumber(int[] nums) {
        int[] k = new int[32];
        for(int num : nums){
            for(int i = 0; i < 32; i++){
                k[i] += num & 1;
                num >>= 1;
            }
        }
        int res = 0;
        for(int i = 0; i < 32; i++){
            res = res | (k[i] % 3) << i;
        }
        return res;
    }
}
