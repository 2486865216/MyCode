package IOtest01;

import java.io.*;

public class test03 {
    public static void main(String[] args) {
        FileOutputStream out = null;
        byte[] a = {97,98,99,100};
        try {
            out = new FileOutputStream("untitled-IO/src/IOtest01/text",true);
            out.write(a);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
