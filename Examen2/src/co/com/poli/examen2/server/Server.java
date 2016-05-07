package co.com.poli.examen2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        System.out.println("server");
        ServerSocket server;
        Socket conexion;
        DataOutputStream salidaDatos;
        DataInputStream entradaDatos;
        String numero = "5656";
        List<String> respuesta = new ArrayList<>();
        Integer counter = 0;
        Integer counter2 = 0;

        try {
            server = new ServerSocket(PUERTO);
            while (true) {
                conexion = server.accept(); //captura conexión con el cliente
                System.out.println("\n");
                System.out.println("IP: " + conexion.getInetAddress().getHostAddress());
                System.out.println("Host: " + conexion.getInetAddress().getHostName());
                entradaDatos = new DataInputStream(conexion.getInputStream());
                salidaDatos = new DataOutputStream(conexion.getOutputStream());
                String numero_cliente = entradaDatos.readUTF();
                System.out.println(numero_cliente);
                if (counter < 9) {
                    counter++;
                    respuesta.clear();
                    if (numero_cliente.length() == 4) {
                        for (int i = 0; i < numero_cliente.length(); i++) {
                            for (int j = 0; j < numero.length(); j++) {
                                if (numero_cliente.charAt(i) == numero.charAt(j) && i == j) {
                                    respuesta.add("*");
                                    counter2++;
                                    break;
                                } else if (numero_cliente.charAt(i) == numero.charAt(j) && i != j) {
                                    respuesta.add("O");
                                    break;
                                }
                            }
                        }
                        if (counter2 == 4) {
                            counter2 = 0;
                            System.out.println("Ganaste");
                            salidaDatos.writeUTF("Ganaste");
                        }
                        Collections.sort(respuesta);
                        System.out.println(respuesta);
                    } else {
                        salidaDatos.writeUTF("Número diferente de 4 dígitos");
                        System.out.println("Número diferente de 4 dígitos");
                    }
                    salidaDatos.writeUTF("Inténtalo de nuevo");
                } else {
                    System.out.println("Ya habías hecho 10 intentos");
                    salidaDatos.writeUTF("Ya habías hecho 10 intentos");
                }
                conexion.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
