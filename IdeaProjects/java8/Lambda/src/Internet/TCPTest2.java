package Internet;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest2 {
    @Test
    public void client() throws IOException {
        Socket socket = null;
        OutputStream os = null;
        FileInputStream is = null;
        try {
            InetAddress byName = InetAddress.getByName("192.168.137.1");
            socket = new Socket(byName, 9000);
            os = socket.getOutputStream();
            is = new FileInputStream(new File("src/tcptest.jpg"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                os.write(buffer, 0 , len);
            }
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
        try {
            serverSocket = new ServerSocket(9000);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            is = new FileOutputStream(new File("tcptest01.jpg"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1){
                is.write(buffer, 0, len);
            }
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
        }
    }
}
