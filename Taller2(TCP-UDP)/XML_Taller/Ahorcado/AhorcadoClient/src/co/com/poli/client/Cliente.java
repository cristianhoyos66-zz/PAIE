package co.com.poli.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    private static final int PUERTO = 12345;
    private static final String IP = "localhost";

    public static void main(String[] args) {
        System.out.println("Cliente");

        Socket conexion;

        DataInputStream entradaDatos;
        DataOutputStream salidaDatos;
        Scanner teclado = new Scanner(System.in);
        try {

            while (true) {
                conexion = new Socket(IP, PUERTO);
                String datoaEnviar = teclado.nextLine();
                if (datoaEnviar.isEmpty()) {
                    System.out.println("Por favor ingresa una letra");
                } else if (datoaEnviar.length() > 1) {
                    System.out.println("Debes ingresar únicamente una letra");
                } else {
                    salidaDatos = new DataOutputStream(conexion.getOutputStream());
                    salidaDatos.writeUTF(datoaEnviar); 
                }
                entradaDatos = new DataInputStream(conexion.getInputStream());
                System.out.println(entradaDatos.readUTF());
                conexion.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
