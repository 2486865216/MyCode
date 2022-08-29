package src.StringTEst;

public class buffer {
    public static void main(String[] args) {
//        stringbuffer线程安全，stringbuild非线程安全
        StringBuffer sb = new StringBuffer(100);//默认16
        sb.append("hello");
        sb.append("word");
        System.out.println(sb);
    }
}
