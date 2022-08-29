package Offer.Another;

/**
 * author ye
 * createDate 2022/5/10  9:02
 */
public class solution2527 {
    public String replaceSpaces(String S, int length) {
        char[] str = S.toCharArray();
        int realLength = length;
        for (int i = 0; i < length; i++) {
            if (str[i] == ' '){
                realLength += 2;
            }
        }
        char[] str1 = new char[realLength];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (str[i] == ' '){
                str1[index++] = '%';
                str1[index++] = '2';
                str1[index++] = '0';
            }else {
                str1[index++] = str[i];
            }
        }
        return String.copyValueOf(str1);
    }
}
