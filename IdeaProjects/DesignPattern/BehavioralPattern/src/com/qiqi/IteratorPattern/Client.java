package com.qiqi.IteratorPattern;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/8  11:45
 */
public class Client {
    public static void main(String[] args) {
        //创建学院
        List<Collage> collages = new ArrayList<>();
        ComputerCollage computerCollage = new ComputerCollage();
        InfoCollage infoCollage = new InfoCollage();
        collages.add(infoCollage);
        collages.add(computerCollage);
        OutputImplements outputImplements = new OutputImplements(collages);
        outputImplements.printCollage();
    }
}
