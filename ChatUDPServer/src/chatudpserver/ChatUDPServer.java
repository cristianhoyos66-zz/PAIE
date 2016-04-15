package chatudpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatUDPServer {

    private static final int PUERTO = 12345;
 
    public static void main(String[] args) {
        DatagramSocket conexion;
        DatagramPacket pckgIn;
        DatagramPacket pckgOut;
        
        byte[] dataIn;
        byte[] dataOut;
        
        Scanner keyboard = new Scanner(System.in);
        String msg = "";
        
        try {
            conexion = new DatagramSocket(PUERTO);
            while (true) {
                dataIn = new byte[1024];
                dataOut = keyboard.nextLine().getBytes();
                pckgIn = new DatagramPacket(dataIn, dataIn.length);
                pckgOut = new DatagramPacket(dataOut, dataOut.length, 
                                                pckgIn.getAddress(), pckgIn.getPort());
                
                conexion.receive(pckgIn); 
                msg = new String(pckgIn.getData());
                System.out.println("Cliente >> " + msg);
                
                conexion.send(pckgOut);
            }
        } catch (SocketException ex) {
            Logger.getLogger(ChatUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
