package com.yuye.OpenClosedPrinciple;

/**
 * author ye
 * createDate 2022/1/25  14:16
 */
public class OpenClosedPrinciple02 {
    public static void main(String[] args) {
        GraphicEditor1 graphicEditor1 = new GraphicEditor1();
        graphicEditor1.drawShape(new Circle1());
        graphicEditor1.drawShape(new Rectangle1());
    }
}
class GraphicEditor1 {
    public void drawShape(Shape1 shape1) {
        shape1.draw();
    }
}
abstract class Shape1{
    int type;
    abstract void draw();
}
class Rectangle1 extends Shape1{
    Rectangle1(){
        super.type = 1;
    }

    @Override
    void draw() {
        System.out.println("矩形");
    }
}
class Circle1 extends Shape1{
    Circle1(){
        super.type = 2;
    }

    @Override
    void draw() {
        System.out.println("圆形");
    }
}
