package Proxy;


interface ClothFactory{
    void productCloth();
}

public class ProxyClothFactory implements ClothFactory{
    private ClothFactory clothFactory;

    public ProxyClothFactory(ClothFactory clothFactory) {
        this.clothFactory = clothFactory;
    }

    @Override
    public void productCloth() {
        System.out.println("代理工厂做事");

        clothFactory.productCloth();

        System.out.println("代理工厂做事做收尾");
    }
}
class NikeClothFactory implements ClothFactory{

    @Override
    public void productCloth() {
        System.out.println("nike工程生产");
    }
}
class proxyTest{
    public static void main(String[] args) {
        //被代理类
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        //代理类
        ClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);
        proxyClothFactory.productCloth();
    }
}