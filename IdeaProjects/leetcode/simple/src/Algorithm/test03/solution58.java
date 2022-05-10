package Algorithm.test03;

public class solution58 {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        String[] s1 = s.split(" ");
        return s1[s1.length - 1].length();
    }
    public int lengthOfLastWord1(String s) {
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ' && len == 0){
                continue;
            }else if (s.charAt(i) == ' '){
                return len;
            }else {
                len++;
            }
        }
        return len;
    }
}
