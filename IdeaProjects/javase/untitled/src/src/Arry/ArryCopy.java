package src.Arry;

public class ArryCopy {
    public static void main(String[] args) {
//        System.arraycopy(源，源起点，目标，目标起点，长度);
        int[] a = {1,2,3,4};
        int[] b = new int[20];
        System.arraycopy(a,0,b,0,a.length);
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
    }
}
