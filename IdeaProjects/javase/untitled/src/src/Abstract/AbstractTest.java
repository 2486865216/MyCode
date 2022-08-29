package src.Abstract;

public class AbstractTest {
    public static void main(String[] args) {
        Animal animal = new Cat();
        animal.move();
    }
}

abstract class Animal{
    public abstract void move();
}

class Cat extends Animal{
    public void move(){
        System.out.println("Cat moving!");
    }
}
