package IOtest01;

import java.io.File;
import java.io.IOException;

public class file {
    public static void main(String[] args) {
        File file = new File("untitled-IO/src/IOtest01/textfile.txt");
        File file2 = new File("untitled-IO/src/IOtest01/a/b/d");
        File file3 = new File("untitled-IO/src/IOtest01");

        System.out.println(file.getName());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.lastModified());

        System.out.println(file.length());

        System.out.println(file.getParent());

        System.out.println(file.getAbsolutePath());
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!file2.exists()){
//            file2.mkdir();
            file2.mkdirs();
        }

        File[] file3list = file3.listFiles();
        for (File file3ls:file3list){
            System.out.println(file3ls.getPath() );
        }
    }
}
