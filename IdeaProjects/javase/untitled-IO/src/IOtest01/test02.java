package IOtest01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test02 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        int s;
        byte[] a = new byte[4];
        try {
            fis = new FileInputStream("untitled-IO/src/IOtest01/text");
            System.out.println("总字节数量："+fis.available());
            while ((s=fis.read(a))!=-1){
                System.out.println(new String(a,0,s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
