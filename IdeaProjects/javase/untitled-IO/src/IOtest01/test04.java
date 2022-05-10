package IOtest01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class test04 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream out = null;
        try {
            fis = new FileInputStream("C:\\Users\\烨\\eclipse-workspace\\Hello word\\src\\helloword.java");
            out = new FileOutputStream("untitled-IO/src/IOtest01/hello.java",true);
            byte[] a = new byte[100*10224];
            int count = 0;
            while((count=fis.read(a))!=-1){
                out.write(a);
            }
            out.flush();
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
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
