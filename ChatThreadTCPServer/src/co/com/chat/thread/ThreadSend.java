package co.com.chat.thread;

import co.com.chat.gui.GUIWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSend implements Runnable {
    
    private Socket con;
    private GUIWindow window;
    private DataOutputStream dataOut;
    private String msg;

    public ThreadSend(Socket con, final GUIWindow window) {
        this.con = con;
        this.window = window;
        this.window.txtMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    msg = window.txtMsg.getText();
                    dataOut.writeUTF(msg);
                    window.txtMsg.setText("");
                } catch (IOException ex) {
                    Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    @Override
    public void run() {
        try {
            dataOut = new DataOutputStream(con.getOutputStream());
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
