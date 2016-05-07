//SERVIDOR

package co.com.poli.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        System.out.println("Server");
        ServerSocket server;
        Socket conexion;
        DataOutputStream salidaDatos;
        DataInputStream entradaDatos;

        try {
            server = new ServerSocket(PUERTO);

            while (true) {
                conexion = server.accept();
                
                entradaDatos = new DataInputStream(conexion.getInputStream());
                String datosCliente = entradaDatos.readUTF();
                System.out.println(datosCliente);
                
                conexion.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
