package chatUDPClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatUDPClient {

    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        DatagramSocket conexion;
        DatagramPacket pckgIn;
        DatagramPacket pckgOut;
        
        byte[] dataIn;
        byte[] dataOut;
        
        Scanner keyboard = new Scanner(System.in);
        String msg;
        try {
            InetAddress IP = InetAddress.getLocalHost();
            conexion = new DatagramSocket();
            
            while (true) {
                dataOut = keyboard.nextLine().getBytes();
                pckgOut = new DatagramPacket(dataOut, dataOut.length, IP, PUERTO);
                dataIn = new byte[1024];
                
                conexion.send(pckgOut);
                pckgIn = new DatagramPacket(dataIn, dataIn.length);
                conexion.receive(pckgIn); 
                msg = new String(pckgIn.getData());
                
                System.out.println("Server >> " + msg);
            }
        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(ChatUDPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatUDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
