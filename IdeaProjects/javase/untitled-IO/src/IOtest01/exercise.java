package IOtest01;

import java.io.*;

public class exercise {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\烨\\Vue");
        File file1 = new File("C:\\Users\\烨\\vue-copy");

        copyDir(file,file1);
    }

    private static void copyDir(File file, File file1) {
        if (file.isFile()) {
            FileInputStream in = null;
            FileOutputStream out = null;

            String path = file1.getAbsolutePath() + file.getAbsolutePath().substring(14);

            try {
                in = new FileInputStream(file);

                out = new FileOutputStream(path);

                int count = 0;
                byte[] bytes = new byte[1024 * 1024];
                while ((count = in.read(bytes)) != -1) {
                    out.write(bytes,0,count);
                }
                out.flush();
                } catch(FileNotFoundException e){
                    e.printStackTrace();
                } catch(IOException e){
                    e.printStackTrace();
                }finally{
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            return;
        }
        File[] files = file.listFiles();
        for (File file2:files){
            if (file2.isDirectory()){
                String filepath = file2.getPath();

                String filepath2 = file1.getAbsolutePath() + filepath.substring(14);

                File newfile = new File(filepath2);
                if (!newfile.exists()){
                    newfile.mkdirs();
                }
            }
            copyDir(file2,file1);
        }
    }
}
