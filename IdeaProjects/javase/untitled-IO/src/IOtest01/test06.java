package IOtest01;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class test06 {
    public static void main(String[] args) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("untitled-IO/src/IOtest01/text",true);
            char[] a = {'我','是','中','国','人'};
            fileWriter.write(a);
            fileWriter.write(a,2,2);

            fileWriter.write("hello");

            fileWriter.write("\n");
            fileWriter.write("hello word");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }    
}
