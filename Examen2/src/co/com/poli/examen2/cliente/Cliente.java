package co.com.poli.examen2.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    private static final int PUERTO = 12345;
    private static final String IP = "localhost";

    public static void main(String[] args) {
        System.out.println("cliente");

        Socket conexion;

        DataOutputStream salidaDatos;
        DataInputStream entradaDatos;
        Scanner keyboard = new Scanner(System.in);
        try {
            while (true) {
                conexion = new Socket(IP, PUERTO);
                String numero = keyboard.nextLine();
                entradaDatos = new DataInputStream(conexion.getInputStream());
                salidaDatos = new DataOutputStream(conexion.getOutputStream());
                salidaDatos.writeUTF(numero);
                String respuesta = entradaDatos.readUTF();
                System.out.println(respuesta);
                //lógica
                conexion.close();
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
