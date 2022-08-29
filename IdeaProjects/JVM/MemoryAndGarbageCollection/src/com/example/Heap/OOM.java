package com.example.Heap;

import com.sun.javafx.image.impl.ByteRgb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author ye
 * createDate 2022/2/12  13:24
 */
public class OOM {
    public static void main(String[] args) throws InterruptedException {
        List<Picture> list = new ArrayList<>();
        while (true){
            try {
                Thread.sleep(20);
            }catch (Exception e){
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }
}
class Picture{
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}
