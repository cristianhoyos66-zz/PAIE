/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.chat.gui;

import co.com.chat.thread.ThreadReceive;
import co.com.chat.thread.ThreadSend;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author sala305
 */
public class GUIWindow extends javax.swing.JFrame {
    
    private static final int PUERTO = 12345;
    private static ServerSocket server;
    private static Socket con;
    private static GUIWindow window;

    /**
     * Creates new form GUIWindow
     */
    public GUIWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMsg = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAnswer = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtMsg.setName("txtMsg"); // NOI18N
        txtMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMsgActionPerformed(evt);
            }
        });

        txtAnswer.setColumns(20);
        txtAnswer.setRows(5);
        txtAnswer.setName("txtAnswer"); // NOI18N
        jScrollPane1.setViewportView(txtAnswer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addComponent(txtMsg))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMsgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMsgActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        window = new GUIWindow();
        window.setVisible(true);
        
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            server = new ServerSocket(PUERTO);
            while (true) {
                con = server.accept(); //Socket
                service.execute(new ThreadReceive(con, window)); //Thread receive
                service.execute(new ThreadSend(con, window)); //Thread send
            }
        } catch (IOException ex) {
            Logger.getLogger(GUIWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        service.shutdown();
    }

   public void setMsg(String msg) {
       this.txtAnswer.append(msg + "\n");
   }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAnswer;
    public javax.swing.JTextField txtMsg;
    // End of variables declaration//GEN-END:variables
}