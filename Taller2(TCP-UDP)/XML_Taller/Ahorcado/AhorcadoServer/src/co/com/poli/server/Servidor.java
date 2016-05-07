package co.com.poli.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private static final int PUERTO = 12345;
    private static final String palabra = "CASA";

    public static void main(String[] args) {
        System.out.println("Server");
        ServerSocket server;
        Socket conexion;
        DataOutputStream salidaDatos;
        DataInputStream entradaDatos;
        char[] resultado = new char[palabra.length()];
        int intentos = palabra.length();
        String res = "";

        try {
            server = new ServerSocket(PUERTO);

            while (true) {
                conexion = server.accept();
                entradaDatos = new DataInputStream(conexion.getInputStream());
                salidaDatos = new DataOutputStream(conexion.getOutputStream());
                if (intentos > 0) {
                    
                    String datosCliente = entradaDatos.readUTF();
                    char letra = Character.toUpperCase(datosCliente.charAt(0));
                    System.out.println(datosCliente);

                    for (int i = 0; i < palabra.length(); i++) {
                        if (palabra.charAt(i) == letra) {
                            resultado[i] = letra;
                        } else if (resultado[i] == "_".charAt(0) || resultado[i] == 0x0) {
                            resultado[i] = "_".charAt(0);
                        }
                    }
                    salidaDatos.writeUTF(Arrays.toString(resultado));
                    intentos--;
                } else {
                    resultado = new char[palabra.length()];
                    intentos = palabra.length();
                    salidaDatos.writeUTF("Tus intentos se han acabado perdiste");
                    System.out.println("Tus intentos se han acabado perdiste");
                }
                conexion.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
