package Algorithm.test01;

public class solution13 {
    public int romanToInt(String s) {
        int n =0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)){
                case 'I':n = n+ 1;break;
                case 'V':n = n+ 5;break;
                case 'X':n = n+ 10;break;
                case 'L':n = n+ 50;break;
                case 'C':n = n+ 100;break;
                case 'D':n = n+ 500;break;
                case 'M':n = n+ 1000;break;
            }
            if (i!=0){
                if ((s.charAt(i)=='V' || s.charAt(i)=='X') && s.charAt(i-1)=='I'){
                    n = n - 2;
                }
                if ((s.charAt(i)=='L' || s.charAt(i)=='C') && s.charAt(i-1)=='X'){
                    n = n - 20;
                }
                if ((s.charAt(i)=='D' || s.charAt(i)=='M') && s.charAt(i-1)=='C'){
                    n = n - 200;
                }
            }
        }
        return n;
    }
}
