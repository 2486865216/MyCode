package Algorithm.test07;

/**
 * author ye
 * createDate 2022/3/15  19:13
 */
public class solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n){
            int sumgas = 0;
            int sumcost = 0;
            int count = 0;
            while (count < n){
                int j = (i + count) % n;
                sumgas += gas[j];
                sumcost += cost[j];
                if (sumcost > sumgas){
                    break;
                }
                count++;
            }
            if (count == n){
                return i;
            }else {
                i = i + count + 1;
            }
        }
        return -1;
    }
}
