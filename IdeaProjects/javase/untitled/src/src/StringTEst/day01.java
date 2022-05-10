package src.StringTEst;

public class day01 {
    public static void main(String[] args) {
//        保存在字符串常量池中的地址
        String str1 = "hello";
        String str2 = "hello";
        System.out.println(str1==str2);

        char c = "abc".charAt(0);
        System.out.println(c);
//      是否含有
        System.out.println("hello_word_java".contains("java"));
//        是否以指定的字符串结尾
        System.out.println("hello_java".endsWith("java"));
        System.out.println("hello_java".startsWith("hello"));
//        判断是否相等并忽略大小写
        System.out.println("abc".equalsIgnoreCase("AbC"));

        System.out.println("".isEmpty());

//        数组length是属性，字符串length()是方法
        System.out.println("String.length()="+"abc".length());

//        截取字符串
        System.out.println("abcdefg".substring(3));

//        去除前后空白
        System.out.println("      dsa          ".trim());
    }
}
