package src.Arry;

public class arr_1 {
    public static void main(String[] args){
        int[] a= new int[10];
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

        Animal a1 = new Animal();
        Animal a2 = new Animal();
        Animal[] animal = new Animal[2];
        animal[0] = a1;
        animal[1] = a2;
        for (int i = 0; i < animal.length; i++) {
            animal[0].move();
            animal[1].move();;
        }
    }
}
class Animal{
    public void move(){
        System.out.println("hello word!");
    }
}
