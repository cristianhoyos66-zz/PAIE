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
    private static char[] resultado = new char[palabra.length()];
    private static int intentos = palabra.length();
    private static Integer aciertos = 0;
    private static ServerSocket server;
    private static Socket conexion;
    private static DataOutputStream salidaDatos;
    private static DataInputStream entradaDatos;

    public static void main(String[] args) {
        System.out.println("Server");
        try {
            server = new ServerSocket(PUERTO);

            while (true) {
                conexion = server.accept();
                entradaDatos = new DataInputStream(conexion.getInputStream());
                salidaDatos = new DataOutputStream(conexion.getOutputStream());
                String datosCliente = entradaDatos.readUTF();
                if (datosCliente.length() == 1) {
                    char letra = Character.toUpperCase(datosCliente.charAt(0));
                    System.out.println("Letra del cliente: " + datosCliente);
                    gameBase(letra);
                    msgs();
                } else {
                    salidaDatos.writeUTF("Debes ingresar una letra"
                            + "\nTus intentos no se verán afectados. \nIntentos: " + intentos);
                }
                conexion.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void restartGame() {
        resultado = new char[palabra.length()];
        intentos = palabra.length();
        aciertos = 0;
    }

    private static void gameBase(char letra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                resultado[i] = letra;
                aciertos++;
            } else if (resultado[i] == "_".charAt(0) || resultado[i] == 0x0) {
                resultado[i] = "_".charAt(0);
            }
        }
        intentos--;
    }

    private static void msgs() {
        try {
            if (intentos != 0 && aciertos != palabra.length()) {
                salidaDatos.writeUTF("Te quedan " + intentos + " intentos\n"
                        + "Resultado: " + Arrays.toString(resultado));
            } else if (intentos != 0 && aciertos == palabra.length()) {
                salidaDatos.writeUTF("Has ganado \nResultado: " + Arrays.toString(resultado)
                        + "\nSi insertas otra letra, volverás a empezar");
                restartGame();
            } else if (intentos == 0 && aciertos == palabra.length()) {
                salidaDatos.writeUTF("Has ganado \nResultado: " + Arrays.toString(resultado)
                        + "\nSi insertas otra letra, volverás a empezar");
            } else {
                salidaDatos.writeUTF("Te quedan " + intentos + " intentos\n"
                        + "Resultado: " + Arrays.toString(resultado)
                        + "\nHas perdido, si insertas otra letra, volverás a empezar");
                restartGame();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
