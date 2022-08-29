package IOtest01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test05 {
    public static void main(String[] args) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("untitled-IO/src/IOtest01/text");
            char[] a = new char[2];
            int count = 0;
            while ((count = fileReader.read(a))!=-1){
                System.out.println(count);
                System.out.println(new String(a,0,count));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (fileReader!=null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
