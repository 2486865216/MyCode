package com.yuye.DependenceInversionPrinciple;

/**
 * author ye
 * createDate 2022/1/25  13:27
 */
public class DependenceInversionPrinciple03 {
    public static void main(String[] args) {
        Test test = new Test();
        test.open(new Chonghong());

        OpenAndClose openAndClose = new OpenAndClose();
        openAndClose.OpenAndClose(new Chonghong02());
        openAndClose.open();

        OpenClose openClose = new OpenClose();
        openClose.setTV(new Chonghong03());
        openClose.open();
    }
}
//通过接口
interface Interface01{
    public void open(Interface02 interface02);
}
interface Interface02{
    public void play();
}
class Chonghong implements Interface02{

    @Override
    public void play() {
        System.out.println("Hello Chonghong");
    }
}
class Test implements Interface01{

    @Override
    public void open(Interface02 interface02) {
        interface02.play();
    }
}
//通过构造函数
interface IOpenAndClose{
    public void open();
}
interface ITV{
    public void play();
}
class OpenAndClose implements IOpenAndClose{
    private ITV itv;
    public void OpenAndClose(ITV itv){
        this.itv = itv;
    }

    @Override
    public void open() {
        this.itv.play();
    }
}
class Chonghong02 implements ITV{
    @Override
    public void play() {
        System.out.println("hello Chonghong02");
    }
}
//通过setter方法传递
interface IOpenClose{
    public void open();
    public void setTV(TV tv);
}
interface TV{
    public void play();
}
class OpenClose implements IOpenClose{
    private TV tv;

    @Override
    public void setTV(TV tv) {
        this.tv = tv;
    }

    @Override
    public void open() {
        tv.play();
    }
}
class Chonghong03 implements TV{
    @Override
    public void play() {
        System.out.println("Hello Chonghong03");
    }
}