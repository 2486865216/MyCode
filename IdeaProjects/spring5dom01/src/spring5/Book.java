package spring5;

public class Book {
    private String bname;
    private String bauthor;

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public static void main(String[] args) {
        Book book = new Book();
        //使用set方法注入
        book.setBname("abc");
    }

    public void testDom() {
        System.out.println("bname="+bname+";"+"bauthor="+bauthor);
    }
}
