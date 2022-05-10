package IOtest01;

import java.io.*;

public class test07 {
    public static void main(String[] args){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("untitled-IO/src/IOtest01/text");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader file = new BufferedReader(fileReader);

        String str;
        try {
            while ((str = file.readLine())!=null){
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
