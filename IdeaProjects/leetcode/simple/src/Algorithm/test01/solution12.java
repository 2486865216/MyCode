package Algorithm.test01;

public class solution12 {
    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] s = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]){
                num = num - values[i];
                stringBuilder.append(s[i]);
            }
        }
        return stringBuilder.toString();
    }
}
