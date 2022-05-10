package com.qiqi.IteratorPattern;

import java.util.Iterator;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/8  11:40
 */
public class OutputImplements {
    //学院集合
    List<Collage> collages;

    public OutputImplements(List<Collage> collages) {
        this.collages = collages;
    }
    //遍历所有学院，然后调用printDepartment输出各个学院的系
    public void printCollage(){
        Iterator<Collage> iterator = collages.iterator();

        while (iterator.hasNext()){
            Collage collage =iterator.next();
            System.out.println("========"+collage.getName()+"========");
            printDepartment(collage.createIterator());
        }
    }
    //输出
    public void printDepartment(Iterator iterator){
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
