package Offer.day22;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/8  18:49
 */
public class Solution01 {
    public int[] singleNumbers(int[] nums) {
        int res = 0;
        for(int num : nums){
            res = res ^ num;
        }
        int des = 1;
        while((des & res) == 0){
            des = des << 1;
        }
        int a = 0, b = 0;
        for(int num : nums){
            if ((num & des) == 0){
                a = a ^ num;
            }else{
                b = b ^ num;
            }
        }
        return new int[]{a, b};
    }
}
