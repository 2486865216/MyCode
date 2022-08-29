package com.yuye.OpenClosedPrinciple;

/**
 * author ye
 * createDate 2022/1/25  14:07
 */
public class OpenClosedPrinciple01 {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
    }
}
class GraphicEditor{
    public void drawShape(Shape shape){
        if (shape.type == 1){
            drawRectangle();
        }
        if (shape.type == 2){
            drawCircle();
        }
    }
    private void drawRectangle(){
        System.out.println("矩形");
    }
    private void drawCircle(){
        System.out.println("圆形");
    }
}
class Shape{
    int type;
}
class Rectangle extends Shape{
    Rectangle(){
        super.type = 1;
    }
}
class Circle extends Shape{
    Circle(){
        super.type = 2;
    }
}