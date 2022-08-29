package src.Fengzhuan;
/*
* 1、所有属性私有化，使用private关键字进行修饰，private表示私有的，修饰的所有数据只能在本类中访问。
* 2、对外提供简单的操作入口，也就是说以后外部程序要想访问age属性，必须通过这些简单的入口进行访问：- 对外提供两个公开的方法，分别是set方法和get方法
*-想修改age属性，调用set方法
*- 想读取age属性，调用get方法
* 3、set方法的命名规范：
*public void setAge(int a){
*   age = a;
* }
*4、get方法的命名规范：
*public int getAge(){
*   return age;
* }
*回想一下，一个属性通常访问的时候包括几种访问形式？
*-第一种方式：想读取这个属性的值，读取get
* - 第二种方式：想修改这个属性的值，修改set
*
* setter , getter方法没有static关键字
* 调用：引用.方法名(形参)
*/
public class User {
    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
