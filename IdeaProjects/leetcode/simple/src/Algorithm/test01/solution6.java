package Algorithm.test01;
/*
 * 分析：
 * PAYPALISHIRING
 * numRows = 4
 * P   I   N             0   6    12
 * A L S I G             1 5 7 11 13
 * Y A H R               2 4 8 10
 * P   I                 3   9
 *
 * numRows = 5
 * P   H                 0   8     16    24
 * A S I                 1 7 9  15 17 23 25
 * Y I R                 2 6 10 14 18 22 26
 * P L I G               3 5 11 13 19 21 27
 * A   N                 4   12    20    28
 *
 * 首先输出头     (0 * 8    ,          , 1 * 8    ,          ,....)
 * 然后输出体     (0 * 8 + 1, 0 * 8 + 7, 1 * 8 + 1, 1 * 8 + 7,....)
 *               (0 * 8 + 2, 0 * 8 + 6, 1 * 8 + 2, 1 * 8 + 6,....)
 *               (0 * 8 + 3, 0 * 8 + 5, 1 * 8 + 3, 1 * 8 + 5,....)
 * 最后输出尾     (0 * 8 + 4,          , 1 * 8 + 4,          ,....)
 *
 * 发现规律就是：除了第一行和最后一行，中间行的列没有缺少的（最后一列除外）;
 * 将第一列和第二列看作一个整体， 原字符串s按照‘V’进行填充，把每个填满‘V’的元素看做一段（segment），
 * 那么一段的元素个数为 2 * numRows - 2(减2是因为第二列的第一行和最后一行没有元素)，
 * 每个元素都可以用a * b + c表示，并且第一列的c1和第二列的c2有c1 + c2 = segment
 * */
public class solution6 {
    public String convert(String s, int numRows){
        int segment = numRows*2-2;
        StringBuilder stringBuilder = new StringBuilder(s.length());
        if (s.length() < numRows || numRows == 1){
            return s;
        }
//        第一行
        for (int i = 0; i*segment < s.length(); i++) {
            stringBuilder.append(s.charAt(i*segment));
        }
//        中间体
        for (int i = 0; i < numRows-2; i++) {
            for (int j = 0; j*segment+i+1 < s.length(); j++) {
                stringBuilder.append(s.charAt(j*segment+i+1));
                if (j*segment+(segment-i-1) < s.length()){
                    stringBuilder.append(s.charAt(j*segment+(segment-i-1)));
                }
            }
        }
//        最后一行
        for (int i = 0; i * segment + numRows - 1 < s.length(); i++) {
            stringBuilder.append(s.charAt(i * segment + numRows - 1));
        }
        return stringBuilder.toString();
    }
}
