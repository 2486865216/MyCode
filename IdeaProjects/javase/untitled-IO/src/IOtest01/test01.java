package IOtest01;

import java.io.*;

public class test01 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:\\Users\\烨\\IdeaProjects\\javase\\untitled-IO\\src\\IOtest01\\text");
            int s= 0;
            while ((s=fis.read())!=-1){
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
