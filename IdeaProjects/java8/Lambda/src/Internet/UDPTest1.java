package Internet;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

public class UDPTest1 {
    @Test
    public void send() throws IOException {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
            String string = "UDP方式";
            byte[] data = string.getBytes();
            InetAddress inetAddress = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, 9090);
            datagramSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (datagramSocket != null){
                datagramSocket.close();
            }
        }
    }

    @Test
    public void receive() throws IOException {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(9090);
            byte[] data = new byte[100];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            datagramSocket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (datagramSocket != null){
                datagramSocket.close();
            }
        }
    }
}
