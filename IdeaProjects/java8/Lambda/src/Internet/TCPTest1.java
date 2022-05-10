package Internet;

import org.junit.Test;
import sun.security.util.Length;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest1 {
    @Test
    public void client() throws IOException {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.137.1");
            socket = new Socket(inetAddress, 8888);
            outputStream = socket.getOutputStream();
            outputStream.write("Hello".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream!=null){
                outputStream.close();
            }
            if (socket != null){
                socket.close();
            }
        }
    }

    @Test
    public void server() throws IOException{
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            serverSocket = new ServerSocket(8888);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buf = new byte[5];
            int len;
            while ((len = inputStream.read(buf)) != -1){
                baos.write(buf, 0, len);
            }
            System.out.println(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                serverSocket.close();
            }
            if (socket != null){
                socket.close();
            }
            if (inputStream != null){
                inputStream.close();
            }
            if (baos != null){
                baos.close();
            }


        }
    }
}
