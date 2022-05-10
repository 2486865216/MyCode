package src.DoutaiTest;

public class DoutaiTest {
    /*3、多态的作用是什么？
        降低程序的耦合度，提高程序的扩展力。
        能使用多态尽量使用多态。
        父类型引用指向子类型对象。
        核心：面向抽象编程，尽量不要面向具体编程
     */public static void main(String[] args){
        Master master = new Master();
        master.feed(new Cat());
        master.feed(new Dog());
        master.feed(new Snake());
    }

}
