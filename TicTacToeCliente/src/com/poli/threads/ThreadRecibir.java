/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poli.threads;

import com.poli.gui.Ventana;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristianhoyos66
 */
public class ThreadRecibir implements Runnable {

    private Socket conexion;
    private Ventana ventana;
    private DataInputStream entrada;
    private String mensaje;

    public ThreadRecibir(Socket conexion, Ventana ventana) {
        this.conexion = conexion;
        this.ventana = ventana;
    }
    
    @Override
    public void run() {
        try {
            entrada = new DataInputStream(conexion.getInputStream());
            while (true) {
                mensaje = entrada.readUTF();
                ventana.setPintarJugada(mensaje);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadRecibir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
