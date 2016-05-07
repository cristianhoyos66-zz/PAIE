/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poli.threads;

import com.poli.gui.Ventana;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;


/**
 *
 * @author cristianhoyos66
 */
public class ThreadEnviar implements Runnable {
    
    private DataOutputStream salida;
    private String mensaje;
    private Socket conexion;
    private final Ventana ventana;

    public ThreadEnviar(Socket conexion, Ventana ventana) {
        this.conexion = conexion;
        this.ventana = ventana;
        
        for (int i = 1; i <= 10; i++) {
            try {
                Class clase = Class.forName("com.poli.gui.Ventana");
                Field campo = clase.getDeclaredField("btn" + i);
                JButton boton = (JButton) campo.get(ventana);
                setEnviar(boton, String.valueOf(i));
            } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ThreadEnviar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public final void setEnviar(JButton boton, final String num) {
       
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 boton.setBackground(Color.cyan);
                 boton.setEnabled(true);
                try {
                    salida.writeUTF(num);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadEnviar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void run() {
        try {
            salida = new DataOutputStream(conexion.getOutputStream()) {};
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(ThreadEnviar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
