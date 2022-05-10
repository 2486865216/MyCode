package src.Interface_Test;

interface A{
    void Al();
}
interface B{
    void Bl();
}
interface C{
    void Cl();
}

public class Test1 {
    public static void main(String[] args){
        A a = new Test_dou();
        B b = new Test_dou();
        C c = new Test_dou();
        a.Al();
        b.Bl();
        c.Cl();

        if (a instanceof B){
            B a1 = (B)a;
            a1.Bl();
        }

        if (b instanceof C){
            C a1 = (C)b;
            a1.Cl();
        }
    }
}
class Test_dou implements A,B,C{
    public void Al() {
        System.out.println("Al");
    }
    public void Bl() {
        System.out.println("Bl");
    }
    public void Cl() {
        System.out.println("Cl");
    }
}

