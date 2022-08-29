package src.ExtendsTest;

public class ExtendsTest {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Cat cat = new Cat();
        Bird bird = new Bird();
        animal.Animalcall();
        cat.Animalcall();
        bird.Animalcall();

//        向上转型（子————》父，自动转型）
        Animal animalcat = new Cat();
        animalcat.Animalcall();
//        向下转型（子有父没有，强制类型转换）
        Animal animalcat1 = new Cat();
        Cat animalcat2 = (Cat)animalcat1;
        animalcat2.Catrun();

/*类型之间不存在任何继承关系，此时出现了著名的异常：
        java.lang.ClassCastException
        类型转换异常，这种异常总是在“向下转型”的时候会发生。
        Cat c3 = (Cat)a3;
            1、以上异常只有在强制类型转换的时候会发生，也就是说“向下转型“存在隐患(编译过了，但是运行错了！)
            2、向上转型只要编译通过，运行一定不会出问题：Animal a = new Cat()
            3、向下转型编译通过，运行可能错误：Animal a3= newBird(); Cat c3 - (Cat) a3;
            4、怎么避免向下转型出现的ClassCastException呢？
                使用instanceof运算符可以避免出现以上的异常。
        5、instanceof运算符怎么用？
            5.1、语法格式：
            (引用 instanceof 数据类型名)
        5.2、以上运算符的执行结果类型是布尔类型，结果可能是true/false
        5.3、关于运算结果true/false：
            假设：(a instanceof Animal)
            true表示：
            a这个引用指向的对象是一个Animal类型。
            false表示：
            a这个引用指向的对象不是一个Animal类型。

            在进行强制类型转换时建议使用instanceof，避免报错
        */
        if (animalcat1 instanceof Cat){
            Cat Cat1 = (Cat)animalcat1;
            Cat1.Catrun();
        }
    }
}
