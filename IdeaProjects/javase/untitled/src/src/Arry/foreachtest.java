package src.Arry;

public class foreachtest {
    public static void main(String[] args) {
        int[] a = {20,30,53,6,2,51,85,45};
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        System.out.println("=====================================");
        for (int data : a){
            System.out.println(data);
        }
    }
}
