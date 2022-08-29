package Algorithm.test01;

public class solution8 {
    public int myAtoi(String s){
        char[] s1  = s.trim().toCharArray();
        int begin = -1;
        int flag = 1;
        Long test=null;
        StringBuilder s3 = new StringBuilder();
        if (s1.length > 0) {
            if (s1[0] == '-'){
                begin = 1;
                flag=-1;
            } else if (s1[0] == '+') {
                begin = 1;
            } else if (s1[0] >= '0' && s1[0] <= '9') {
                begin = 0;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
        for (int i = begin; i < s1.length; i++) {
            if (s1[i]<='9' && s1[i]>='0'){
                s3.append(s1[i]);
            }else {
                break;
            }
        }
        if (s3.equals("")){
            return 0;
        }else {
            try {
                test = Long.parseLong(s3.toString());
            }catch (Exception e){
                if (flag>0){
                    return Integer.MAX_VALUE;
                }
                else {
                    return Integer.MIN_VALUE;
                }
            }
            if (Long.parseLong(s3.toString()) <= Integer.MAX_VALUE){
                return Integer.parseInt(s3.toString())*flag;
            }else{
                if (flag>0){
                    return Integer.MAX_VALUE;
                }
                else {
                    return Integer.MIN_VALUE;
                }
            }
        }
    }
}
