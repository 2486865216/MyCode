package Algorithm.test02;

public class solution38 {
    public String countAndSay(int n) {
        String str = "1";
        for (int i = 2; i <=n ; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int left = 0;
            int right = 0;
            while (right < str.length()){
                while (right < str.length() && str.charAt(right) == str.charAt(left)){
                    right++;
                }
                stringBuilder.append(right - left).append(str.charAt(left));
                left = right;
            }
            str = stringBuilder.toString();
        }
        return str;
    }
}
