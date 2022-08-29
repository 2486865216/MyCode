package Internet;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest3 {
    @Test
    public void client() throws IOException {
        Socket socket = null;
        OutputStream os = null;
        FileInputStream is = null;
        try {
            InetAddress byName = InetAddress.getByName("192.168.137.1");
            socket = new Socket(byName, 11111);
            os = socket.getOutputStream();
            is = new FileInputStream(new File("src/tcptest.jpg"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                os.write(buffer, 0 , len);
            }
            socket.shutdownOutput();
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[20];
            int len1;
            while ((len1 = inputStream.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes, 0, len1);
            }
            System.out.println(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket!=null){
                socket.close();
            }
            if (is!=null) {
                is.close();
            }
            if (os!=null){
                os.close();
            }
        }
    }

    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        FileOutputStream is = null;
        OutputStream os = null;
        try {
            serverSocket = new ServerSocket(11111);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            is = new FileOutputStream(new File("tcptest02.jpg"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1){
                is.write(buffer, 0, len);
            }
            os = socket.getOutputStream();
            os.write("已接受".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket!=null){
                serverSocket.close();
            }
            if (socket!=null){
                socket.close();
            }
            if (inputStream!=null){
                inputStream.close();
            }
            if (is!=null){
                is.close();
            }
            if (os!=null){
                os.close();
            }
        }
    }
}
